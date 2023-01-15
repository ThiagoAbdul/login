import { emailInvalido, isSenhaFraca } from "./validar.js"

const btnCadastrar = document.querySelector("#btn-cadastrar")

const getEmail = () => cadastroEmail.value
const getSenha = () => cadastroSenha.value
const getConfirmarSenha = () => confirmarSenha.value

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
