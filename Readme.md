# Sistema Paraquedas (CraftBukkit 1.6.4 R3)

Plugin simples de **paraquedas** para servidores **Minecraft 1.6.4** (CraftBukkit `v1_6_R3`).

Ao usar o item do paraquedas, o player abre o paraquedas no ar e desce mais devagar, com bloqueio básico de fly e opção de consumir “cordas” (quantidade configurável).

---

## ✅ Compatibilidade

- **Minecraft:** 1.6.4  
- **Server:** CraftBukkit **v1_6_R3**
- Observação: o plugin aplica um *patch* de som para garantir o `Sound.HORSE_ARMOR` mapeado para `mob.horse.armor` nessa versão.

---

## ✨ Funcionalidades

- Abrir/fechar paraquedas com **clique direito**
- Descida controlada (velocidade configurável)
- Som ao abrir e fechar
- Anti-fly quando o paraquedas está ativo
- Opção de consumir item/“corda” ao usar (configurável)
- Comando para recarregar config sem reiniciar o servidor

---

## 🎮 Como usar

1. Pegue o item do paraquedas (ID configurado no código; padrão: `9719`).
2. **Clique com o botão direito** no ar/bloco enquanto estiver no ar para abrir.
3. Clique com o botão direito novamente para fechar (ou ele fecha automaticamente ao tocar o chão).

---

## ⌨️ Comandos

- `/paraquedas reload`  
  Recarrega o `config.yml`.

---


## 🔐 Permissões

- `paraquedas.reload` → permite usar `/paraquedas reload`
- `paraquedas.admin` → permissão “admin” (bypass/op)

---

## ⚙️ Configuração (`plugins/SistemaParaquedas/config.yml`)

```yml
FastDescend: 0.2
SlowDescend: 0.01
ForwardSpeed: 0.2

UseUpString:
  Enabled: false
  Amount: 10
```

https://github.com/user-attachments/assets/39ab2bf7-ded1-4919-a316-8041e02b1df2
  
