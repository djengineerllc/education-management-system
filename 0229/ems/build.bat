@echo ======================= START BUILD PROJECT=========================
lib\ext\jsbuilder\JSBuilder.bat --projectFile build.jsb3 --deployDir  ./deploy/ems --verbose > build.log & java -jar ./lib/ext/jsbuilder/ycompressor/ycompressor.jar --type css --charset utf8 -o deploy/ems/src/ems/core/resources/css/ems-all.css deploy/ems/src/ems/core/resources/css/ems-all.css
@echo ======================= BUILD PROJECT DONE =========================
