# gitflowtest
test repository

## branch
* main
* develop
* autocreate

## 自動生成までのブランチの動き
* git clone
git clone https://github.com/rhn-consulting-kemori/gitflowtest.git

* developブランチ作成
git checkout -b develop
git push --set-upstream origin develop

* autocreateブランチ作成
git checkout -b autocreate

* 入力ファイルをコミット
git add .
git commit -m ""
git push

* autocreate->developブランチへのMerge
git checkout develop
git merge autocreate
git push

* autocreateブランチ削除
git branch -d autocreate

## developブランチでの編集
main 
- resources
-- application.yaml
- rule
-- ~Rule.java

test
- resources
-- application.yaml
-- format_check_rule_test.csv
- rule
-- ~RuleTest.java
- route
-- RouteProcessTest.java
-- RouteProcessTestNormalDataSet.java
-- RouteProcessTestErrorDataSet.java