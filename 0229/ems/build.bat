@rem 打包步骤
@rem  1. 执行 build.bat
@rem  2. 修改 deploy/ems/index.html 中的 config="productMode:false" 为产品模式 config="productMode:true"

@echo ========================================================
@echo ==               START BUILD EMS PROJECT              ==
@echo ========================================================

lib\ext\jsbuilder\JSBuilder.bat --projectFile build.jsb3 --deployDir  ./deploy/ems --verbose > build.log & java -jar ./lib/ext/jsbuilder/ycompressor/ycompressor.jar --type css --charset utf8 -o deploy/ems/src/ems/core/resources/css/ems-all.css deploy/ems/src/ems/core/resources/css/ems-all.css