## 要在 GitHub 上创建一个仓库，并与他人协作，以下是详细的步骤。

### 1. 你作为仓库创建者的操作步骤：

**（1）创建 GitHub 仓库：**

  1. 登录 GitHub 账户，点击页面右上角的 `+` 号，选择 **New repository**。
  2. 输入仓库名称，选择是否公开 (Public) 或私有 (Private)，然后点击 **Create repository**。

**（2）在 IntelliJ IDEA 中将项目连接到 GitHub 仓库：**

  1. 打开 IntelliJ IDEA，加载你的项目。
  2. 通过 `VCS` 菜单选择 **Enable Version Control Integration**，选择 `Git` 作为版本控制系统。
  3. 右键点击项目目录，在弹出的菜单中选择 `Git` -> **Commit Directory**，并完成初次提交。
  4. 设置远程仓库：点击 `Git` 面板中的 **Push** 按钮，然后在弹出的窗口中选择 `Define remote`，输入 GitHub 仓库的 URL，并命名为 `origin`。
  5. Push 代码到 GitHub：在 `Git` 面板中，选择 **Push**，将本地代码推送到 GitHub。

**（3）邀请协作者加入 GitHub 仓库：**

  1. 进入你刚刚创建的 GitHub 仓库主页，点击 **Settings**。
  2. 选择左侧菜单的 **Collaborators**，然后点击 **Add people**。
  3. 输入协作者的 GitHub 用户名，邀请他们加入。

### 2. 另一位协作者的操作步骤：

**（1）接受邀请：**

  1. 协作者会收到来自 GitHub 的邀请通知，点击接受邀请即可获得仓库的访问权限。

**（2）克隆仓库：**
  1. 协作者登录 GitHub，找到你的仓库，点击 **Code** 按钮，复制仓库的 HTTPS 或 SSH 链接。
  2. 在 IntelliJ IDEA 中选择 `File` -> **New** -> **Project from Version Control**。
  3. 输入仓库链接并选择本地保存路径，点击 **Clone**，将远程仓库克隆到本地。

**（3）协作者在 IntelliJ IDEA 中编写代码：**
  1. 进行修改后，协作者可以在 `Git` 面板中选择 **Commit** 来提交本地修改。
  2. 提交完成后，使用 **Push** 将代码推送到远程仓库。

**（4）与主仓库同步：**

  1. 协作者在开发前可以通过 `Git` 面板的 **Pull** 操作来同步最新的代码，避免冲突。
  2. 如果协作中遇到冲突，IDEA 提供了直观的冲突解决界面，可以手动合并冲突后继续提交。

通过这些步骤，你和协作者就可以顺利在 IntelliJ IDEA 和 GitHub 上进行代码协作。



```git
git remote add origin https://github.com/your_repository_name
git branch -M main
git push -u origin main
```



### 参考资料：

[Git学习笔记](https://ideepspace.gitbooks.io/git/content/)

[记Git报错-refusing to merge unrelated histories](https://blog.csdn.net/u012145252/article/details/80628451)

