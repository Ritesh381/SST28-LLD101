NotificationSender is used in sub-classes EmailSender, SmsSender, WhatsAppSender

Email and WhatsApp have some precondition to send the message. Sms dosen't have any precondition for cheking number of message size. 

Children behave differently
- Email cuts message text
- Whatsapp throws exception for wrong phone format
- Sms ignores subject

Behavior of sub classes 
- checks null
- extra validation (in whatsapp for phone number)
- print
- add to audit

if we see the individual services should have control over extraValidation and printing only. Flow should be same in all.

So now flow of Main NotificationSender send()

Check for null --> apply extra validation --> format string and print -->add message to audit.

