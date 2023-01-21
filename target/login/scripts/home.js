const uploadImagem = document.querySelector("#upload-imagem")
const texto = document.querySelector("#texto")

const getArquivoImagem = (input) => {
    const imagemCarregada = input.files[0]
    const file = new FileReader()
    file.addEventListener('load', () => {
        document.querySelector("#container-imagem").style.backgroundImage = `url(${file.result})`
        texto.style.display = "none"
        if(confirm("Deseja atualizar sua foto de perfil?")){
            formFoto.submit()
        }
        else{
            document.querySelector("#container-imagem").style.backgroundImage = 'none'
            texto.style.display = 'inline'
        }
    })
    file.readAsDataURL(imagemCarregada)
}

uploadImagem.addEventListener('change', e => {
    getArquivoImagem(e.target)
})