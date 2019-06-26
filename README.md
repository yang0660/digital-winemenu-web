Git global setup
git config --global user.name "zhuhecheng"
git config --global user.email "442119101@qq.com"

Create a new repository
git clone http://47.105.134.136:7999/zhuhecheng/digital-winemenu-web.git
cd digital-winemenu-web
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master

Push an existing folder
cd existing_folder
git init
git remote add origin http://47.105.134.136:7999/zhuhecheng/digital-winemenu-web.git
git add .
git commit -m "Initial commit"
git push -u origin master

Push an existing Git repository
cd existing_repo
git remote rename origin old-origin
git remote add origin http://47.105.134.136:7999/zhuhecheng/digital-winemenu-web.git
git push -u origin --all
git push -u origin --tags