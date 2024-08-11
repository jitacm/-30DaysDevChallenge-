require('dotenv').config();

const fs = require('fs')

const qrcode = require('qrcode-terminal')
const { Client } = require('whatsapp-web.js')

const appid = process.env.APPID
const WolframAlphaAPI = require('./lib/WolframAlphaAPI.js')
let wraAPI = WolframAlphaAPI(appid)
const handleImage = require('./handlers/handleImage')

// all bot commands start like "!b ..."
const invokeKey = '!b'

// Path where the session data will be stored
const SESSION_FILE_PATH = './session.json'

// Load the session data if it has been previously saved
let sessionData
if (fs.existsSync(SESSION_FILE_PATH)) {
  sessionData = require(SESSION_FILE_PATH)
}

// open whatsapp web in a headless browser (no gui)
const puppeteerOptions = {
  headless: true,
  args: ['--no-sandbox'],
}

// initialize client object
const client = new Client({
  puppeteer: puppeteerOptions,
  session: sessionData,
})

// prints QR code to console when received
client.on('qr', (qr) => {
  qrcode.generate(qr, { small: true })
})

// Save session values to the file upon successful auth
client.on('authenticated', (session) => {
  sessionData = session
  fs.writeFile(SESSION_FILE_PATH, JSON.stringify(session), function (err) {
    if (err) {
      console.error(err)
    }
  })
})

client.on('ready', () => {
  console.log('Client is ready!')
})

// listen for new messages
client.on('message_create', (message) => {
  const messageBody = message.body
  if (messageBody.startsWith(invokeKey)) {
    messageHandler(message)
  }
})
// receive all bot commands and reply accordingly
const messageHandler = (message) => {
  // get message body and trim invokeKey
  let query = message.body.substring(invokeKey.length + 1)
  console.log(`Querying result for ${query}`)
  handleImage(message, query, wraAPI)
}

client.initialize()
