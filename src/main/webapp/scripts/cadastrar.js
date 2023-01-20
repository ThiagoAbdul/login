import { regex, emailInvalido, senhaPossui, isSenhaFraca } from "./validar.js"

const getElemento = (id) => document.querySelector(id)

const btnCadastrar = document.querySelector("#btn-cadastrar")
const divRequisitosSenha = document.querySelector("#requisitos-senha")
const divStatusSenha = document.querySelector("#status-senha")
const paragrafoStatusSenha = document.querySelector("#status-senha p")

const getEmail = () => cadastroEmail.value
const getSenha = () => cadastroSenha.value
const getConfirmarSenha = () => confirmarSenha.value

const confirmarRequisito = (paragrafoRequisito) => {
    const classesDoParagrafo = paragrafoRequisito.classList
    if(classesDoParagrafo.contains('check') == false){
        classesDoParagrafo.add('check')
        classesDoParagrafo.remove('uncheck')
    }
}

const negarRequisito = (paragrafoRequisito) => {
    const classesDoParagrafo = paragrafoRequisito.classList
    if(classesDoParagrafo.contains('check')){
        classesDoParagrafo.add('uncheck')
        classesDoParagrafo.remove('check')
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
    divRequisitosSenha.style.display = "block"
    divStatusSenha.style.display = "none"
}

const resumirStatusDaSenha = () => {
    divStatusSenha.style.display = "block"
    divRequisitosSenha.style.display = "none"
}

cadastroSenha.addEventListener('focus', mostrarRequisitosDaSenha)

cadastroSenha.addEventListener('keyup', () => {
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_MINUSCULO']), 
                        getElemento('#caracter-minusculo'))
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_MAIUSCULO']), 
                        getElemento('#caracter-maiusculo'))
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_ESPECIAL']), 
                        getElemento('#caracter-especial'))
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_NUMERICO']), 
                        getElemento('#caracter-numerico'))
    marcarRequisito(verificarSenhaFactory(regex['DOZE_CARACTERES']), 
                        getElemento('#doze-caracteres'))                    
})

cadastroSenha.addEventListener('blur', () => {
    if(isSenhaFraca(getSenha())){
        paragrafoStatusSenha.innerHTML = "Senha não atende os requisítos"
        negarRequisito(paragrafoStatusSenha)
    }
    else{
        paragrafoStatusSenha.innerHTML = "Senha atende os requisítos"
        confirmarRequisito(paragrafoStatusSenha)
    }
    resumirStatusDaSenha()
})

btnCadastrar.addEventListener('click', () => {
    if(emailInvalido(getEmail())){
        alert('Favor, preencher o e-mail corretamente.')
    }
    else if(isSenhaFraca(getSenha())){
        alert('Senha não atende os requisitos.')
    }
    else if(getSenha() !== getConfirmarSenha()){
        alert('Senhas não coincidem.')
    }
    else{
        formCadastro.submit()
    } 
})
