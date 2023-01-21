import { regex, preencheu, telefoneInvalido, emailInvalido, senhaPossui, senhaFraca } from "./validar.js"

const getTelefone = () => telefone.value
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

const senhasDiferentes = () => getSenha() !== getConfirmarSenha()

cadastroSenha.addEventListener('focus', mostrarRequisitosDaSenha)

cadastroSenha.addEventListener('keyup', () => {
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_MINUSCULO']), caracterMinusculo)
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_MAIUSCULO']), caracterMaiusculo)
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_ESPECIAL']), caracterEspecial)
    marcarRequisito(verificarSenhaFactory(regex['CARACTER_NUMERICO']), caracterNumerico)
    marcarRequisito(verificarSenhaFactory(regex['DOZE_CARACTERES']), dozeCaracteres)                    
})

cadastroSenha.addEventListener('blur', () => {
    if(senhaFraca(getSenha())){
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
    if(senhasDiferentes()){
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
    if(telefoneInvalido(getTelefone())){
        alert('Favor, preencher o telefone corretamente.')
        telefone.focus()
    }
    else if(emailInvalido(getEmail())){
        alert('Favor, preencher o e-mail corretamente.')
        cadastroEmail.focus()
    }
    else if(senhaFraca(getSenha())){
        alert('Senha não atende os requisitos.')
        cadastroSenha.focus()
    }
    else if(senhasDiferentes()){
        alert('Senhas não coincidem.')
        confirmarSenha.focus()
    }
    else{
        formCadastro.submit()
    } 
})
