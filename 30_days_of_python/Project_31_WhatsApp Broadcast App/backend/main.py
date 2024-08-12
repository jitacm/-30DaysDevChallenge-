from fastapi import FastAPI, HTTPException, Depends
from sqlalchemy import create_engine, Column, Integer, String
# from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, Session , declarative_base
from sqlalchemy.ext.mutable import MutableList
from sqlalchemy import JSON
from pydantic import BaseModel
from fastapi.middleware.cors import CORSMiddleware
import requests
import json
from fastapi.responses import JSONResponse
from dotenv import load_dotenv
import os

load_dotenv()

# Initialize FastAPI
# Database setup
SQLALCHEMY_DATABASE_URL = os.getenv('SQLALCHEMY_DATABASE_URL')
engine = create_engine(SQLALCHEMY_DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()



# Contact model
class Contact(Base):
    __tablename__ = "contacts"
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    email = Column(String, unique=True, index=True)
    phone = Column(String, unique=True, index=True)
    tags = Column(MutableList.as_mutable(JSON), default=[])

Base.metadata.create_all(bind=engine)

# Pydantic model
class ContactCreate(BaseModel):
    name: str
    email: str
    phone: str
    tags: list[str] = []

class ContactRead(ContactCreate):
    id: int
    phone:str

# Dependency
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Adjust this to specific origins in production
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.post("/contacts/", response_model=ContactRead)
def create_contact(contact: ContactCreate, db: Session = Depends(get_db)):
    existing_contact = db.query(Contact).filter(
        (Contact.email == contact.email) | (Contact.phone == contact.phone)
    ).first()

    if existing_contact:
        raise HTTPException(
            status_code=400, detail="Contact with this email or phone already exists"
        )

    db_contact = Contact(**contact.model_dump())
    db.add(db_contact)
    db.commit()
    db.refresh(db_contact)
    return db_contact

@app.get("/contacts/", response_model=list[ContactRead])
def read_contacts(skip: int = 0, limit: int = 10, tag: str = None, db: Session = Depends(get_db)):
    query = db.query(Contact)
    if tag:
        query = query.filter(Contact.tags.contains([tag]))
    contacts = query.offset(skip).limit(limit).all()
    return contacts

@app.get("/contacts/{phone_no}", response_model=ContactRead)
def read_contact(phone_no: str, db: Session = Depends(get_db)):
    contact = db.query(Contact).filter(Contact.phone == phone_no).first()
    if contact is None:
        raise HTTPException(status_code=404, detail="Contact not found")
    return contact

@app.delete("/contacts/{phone}")
def delete_contact(phone: str, db: Session = Depends(get_db)):
    contact = db.query(Contact).filter(Contact.phone == phone).first()
    if contact is None:
        raise HTTPException(status_code=404, detail="Contact not found")
    db.delete(contact)
    db.commit()
    return {"ok": True}

@app.put("/contacts/{contact_id}", response_model=ContactRead)
def update_contact(contact_id: int, contact: ContactCreate, db: Session = Depends(get_db)):
    db_contact = db.query(Contact).filter(Contact.id == contact_id).first()
    if db_contact is None:
        raise HTTPException(status_code=404, detail="Contact not found")
    
    existing_contact = db.query(Contact).filter(
        (Contact.id != contact_id) &
        ((Contact.email == contact.email) | (Contact.phone == contact.phone))
    ).first()

    if existing_contact:
        raise HTTPException(
            status_code=400, detail="Contact with this email or phone already exists"
        )
    
    for key, value in contact.model_dump().items():
        setattr(db_contact, key, value)
    db.commit()
    db.refresh(db_contact)
    return db_contact



@app.get("/contacts/phone/{phone}", response_model=ContactRead)
def read_contact_by_phone(phone: str, db: Session = Depends(get_db)):
    contact = db.query(Contact).filter(Contact.phone == phone).first()
    if contact is None:
        raise HTTPException(status_code=404, detail="Contact not found")
    return contact

# CORS configuration

# Replace with your actual WhatsApp Business API endpoint and token

access_token = os.getenv('access_token')
BUSINESS_ACCOUNT_ID = os.getenv('BUSINESS_ACCOUNT_ID')
PHONE_NUMBER_ID= os.getenv('PHONE_NUMBER_ID')

class input(BaseModel):
    recipients: list[str]
    template:str

@app.post("/send-template-message/")
async def send_template_message(request:input):

    print(request)
    headers = {
        "Authorization": f"Bearer {access_token}",
        "Content-Type": "application/json"
    }

    success_count = 0
    errors = []

    for recipient in request.recipients:
        data = {
            "messaging_product": "whatsapp",
            "to": recipient,
            "type":"template",
            "template": {
                "name": request.template,
                "language": {
                    "code": "en_US"
                }
            }
        }

        response = requests.post(url=f"https://graph.facebook.com/v20.0/{PHONE_NUMBER_ID}/messages", headers=headers, data=json.dumps(data))

        print(response.status_code)
        print(response.json())

        if response.status_code == 200:
            success_count += 1
        else:
            errors.append({"recipient": recipient, "error": response.json()})
    
    
    return {
        "status": "completed",
        "successful_messages": success_count,
        "errors": errors
    }





@app.get("/templates")
def get_templates():
    API_URL = f'https://graph.facebook.com/v15.0/{BUSINESS_ACCOUNT_ID}/message_templates'
    headers = {
        'Authorization': f'Bearer {access_token}'
    }

    response = requests.get(API_URL, headers=headers)
    
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.text)

    data = response.json()

  

    # Extract template names
    template_names = [template['name'] for template in data.get('data', [])]

    

    return JSONResponse(content=template_names)



   