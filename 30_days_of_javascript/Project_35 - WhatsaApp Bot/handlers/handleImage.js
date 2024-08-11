const parseDataUrl = require('parse-data-url')
const { MessageMedia } = require('whatsapp-web.js')
const handleImage = (message, text, wraAPI) => {
  try {
    console.log('Image request')
    // send the rest of the message to Wolfram|Alpha API
    wraAPI
      .getSimple({
        i: text,
        timeout: 2,
      })
      .then((res) => {
        const parsed = parseDataUrl(res)
        message.reply(new MessageMedia(parsed.contentType, parsed.data))
      })
      .catch((err) => {
        message.reply(String(err))
      })
  } catch (err) {
    console.log(err)
  }
}
module.exports = handleImage
