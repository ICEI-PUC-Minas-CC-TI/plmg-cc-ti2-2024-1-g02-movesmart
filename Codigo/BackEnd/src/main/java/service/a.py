import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

def enviar_email(destinatario, assunto, mensagem):
    remetente = 'seu_email@gmail.com'
    senha = 'sua_senha'

    msg = MIMEMultipart()
    msg['From'] = remetente
    msg['To'] = destinatario
    msg['Subject'] = assunto

    msg.attach(MIMEText(mensagem, 'plain'))

    with smtplib.SMTP('smtp.gmail.com', 587) as smtp:
        smtp.starttls()
        smtp.login(remetente, senha)
        smtp.send_message(msg)

destinatario = 'destinatario@gmail.com'
assunto = 'Teste de e-mail'
mensagem = 'Olá, isso é apenas um teste de e-mail.'

enviar_email(destinatario, assunto, mensagem)