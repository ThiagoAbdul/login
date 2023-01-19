import { emailInvalido, isSenhaFraca } from "./validar.js"

const btnCadastrar = document.querySelector("#btn-cadastrar")
const divRequisitosSenha = document.querySelector("#requisitos-senha")
const divStatusSenha = document.querySelector("#status-senha")

const getEmail = () => cadastroEmail.value
const getSenha = () => cadastroSenha.value
const getConfirmarSenha = () => confirmarSenha.value

const mostrarRequisitosDaSenha = () => {
    divRequisitosSenha.style.display = "block"
    divStatusSenha.style.display = "none"
}

const resumirStatusDaSenha = () => {
    divStatusSenha.style.display = "block"
    divRequisitosSenha.style.display = "none"
}

cadastroSenha.addEventListener('focus', mostrarRequisitosDaSenha)

cadastroSenha.addEventListener('blur', resumirStatusDaSenha)

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
