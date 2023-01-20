import { regex, preencheu, emailInvalido, senhaPossui, isSenhaFraca } from "./validar.js"

const getEmail = () => cadastroEmail.value
const getSenha = () => cadastroSenha.value
const getConfirmarSenha = () => confirmarSenha.value

const confirmarRequisito = (paragrafoRequisito) => {
    if(paragrafoRequisito.classList.contains('check') === false){
        paragrafoRequisito.classList.add('check')
        paragrafoRequisito.classList.remove('uncheck')
    }
}

const negarRequisito = (paragrafoRequisito) => {
    if(paragrafoRequisito.classList.contains('check')){
        paragrafoRequisito.classList.add('uncheck')
        paragrafoRequisito.classList.remove('check')
    }
}

const marcarRequisito = (requisitoAtendido, paragrafoRequisito) => {
    
    if(requisitoAtendido()){
        confirmarRequisito(paragrafoRequisito)
    }
    else{
        negarRequisito(paragrafoRequisito)
    }
}

const verificarSenhaFactory = (regex) => () => senhaPossui(getSenha(), regex)

const mostrarRequisitosDaSenha = () => {
    requisitosSenha.style.display = "block"
    statusSenha.style.display = "none"
}

const resumirStatusDaSenha = () => {
    statusSenha.style.display = "block"
    requisitosSenha.style.display = "none"
}

const isSenhasDiferentes = () => getSenha() !== getConfirmarSenha()

cadastroSenha.addEventListener('focus', mostrarRequisitosDaSenha)

cadastroSenha.addEventListener('keyup', () => {
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_MINUSCULO']), caracterMinusculo)
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_MAIUSCULO']), caracterMaiusculo)
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_ESPECIAL']), caracterEspecial)
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_NUMERICO']), caracterNumerico)
    marcarRequisito(verificarSenhaFactory(regex['DOZE_CARACTERES']), dozeCaracteres)                    
})

cadastroSenha.addEventListener('blur', () => {
    if(isSenhaFraca(getSenha())){
        statusSenha.innerHTML = "Senha não atende os requisítos"
        negarRequisito(statusSenha)
    }
    else{
        statusSenha.innerHTML = "Senha atende os requisítos"
        confirmarRequisito(statusSenha)
    }
    resumirStatusDaSenha()
})

confirmarSenha.addEventListener('focus', () => mensagemSenhaConfirmada.style.display = "block")

confirmarSenha.addEventListener('keyup', () => {
    if(isSenhasDiferentes()){
        mensagemSenhaConfirmada.innerHTML = "Senhas divergentes."
        negarRequisito(mensagemSenhaConfirmada)
    }
    else{
        if(preencheu(getSenha())){
            mensagemSenhaConfirmada.innerHTML = "Senhas conferem."
            confirmarRequisito(mensagemSenhaConfirmada)
        }
    }
})

confirmarSenha.addEventListener('blur', () => {
    if(!preencheu(getConfirmarSenha())) mensagemSenhaConfirmada.style.display = "none"
})

btnCadastrar.addEventListener('click', () => {
    if(emailInvalido(getEmail())){
        alert('Favor, preencher o e-mail corretamente.')
    }
    else if(isSenhaFraca(getSenha())){
        alert('Senha não atende os requisitos.')
    }
    else if(isSenhasDiferentes()){
        alert('Senhas não coincidem.')
    }
    else{
        formCadastro.submit()
    } 
})
