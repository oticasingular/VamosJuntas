<h1 align="center">👭 Vamos Juntas</h1>

<p align="center">Um app de mulheres e para todas as mulheres. <br> Vamos juntas em segurança? 💜</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=android&logoColor=white"/>
  <img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black"/>
  <img src="https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white"/>
</p>

---

## ✨ Sobre o projeto

**Vamos Juntas** é um aplicativo Android que visa conectar mulheres para que possam voltar para casa juntas durante a noite, promovendo segurança, acolhimento e autonomia por meio da tecnologia.

🛡️ Login com Google  
📍 Cadastro de preferências de rota  
☁️ Integração com Firebase (Auth + Firestore)  
🎨 Interface moderna com Material 3 + Jetpack Compose  
💖 Criado com carinho e responsabilidade

---

## 📸 Imagem de destaque *(adicione a sua aqui!)*

<p align="center">
  <img src="https://user-images.githubusercontent.com/00000000/mockup-vamosjuntas.png" width="300px"/>
</p>

---

## 🛠️ Tecnologias utilizadas

| Tecnologia                                                       | Descrição                                 |
|------------------------------------------------------------------|-------------------------------------------|
| [Kotlin](https://kotlinlang.org/)                                | Linguagem moderna, concisa e segura       |
| [Jetpack Compose](https://developer.android.com/jetpack/compose) | UI declarativa para Android               |
| [Firebase Auth](https://firebase.google.com/products/auth)       | Login com Google                          |
| [Firestore](https://firebase.google.com/products/firestore)      | Banco de dados em tempo real              |
| [Material 3](https://m3.material.io/)                            | Componentes visuais modernos              |
| [Accompanist](https://google.github.io/accompanist/)             | Navegação com animação suave              |

---

## ⚙️ Funcionalidades

- 🔐 Autenticação via Google
- 👤 Cadastro automático da usuária no Firestore
- 📌 Cadastro e edição de **rotas preferidas**
- 🧭 Transições animadas com Compose Navigation
- ☁️ Salvamento de dados persistentes na nuvem

---

## 💡 Funcionalidades futuras

- 🔍 Buscar caronas compatíveis por rota e horário
- 🗺️ Exibir mapa com rotas sugeridas
- 📨 Enviar notificações push
- 🧚 Tela de perfil com histórico de caronas

---

## 📄 Licença

Projeto acadêmico para fins educativos.
Se quiser adaptar para produção, leve em conta LGPD, ética e privacidade das usuárias.
Compartilhe com outras mulheres desenvolvedoras! 👭

---
<details>
  <summary>📂 Estrutura do projeto (clique para expandir)</summary>

```bash
firstapp/
├── MainActivity.kt
├── LoginScreen.kt
├── PreferenciasScreen.kt
├── model/
│   ├── User.kt
│   └── PreferenciasRota.kt
├── ui/
│   └── theme/
│       ├── Theme.kt
│       └── Color.kt
├── res/
│   ├── layout/
│   └── values/
└── google-services.json (🚫 privado)

