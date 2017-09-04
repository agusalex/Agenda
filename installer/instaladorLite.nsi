

#Nombre del directorio
!define NOMBRE "Agenda"
#Tamaño necesario para la instalacion
!define INSTALLSIZE 150000000
#Directorio de instalacion
InstallDir "$PROGRAMFILES\${NOMBRE}"

#Nombre del instalador
outFile "instaler.exe"

#Paginas que queremos usar
 Page license
 #Page components
 Page directory
 Page instfiles
 
 #Lo que va a decir la licencia
 LicenseData "Readme.txt"


Section "instaladorJar"
	#seteamos el destino
	setOutPath $INSTDIR
	#Elegimos los archivos que queremos instalar, en caso de que sea una carpeta ponemos /r + "nombreCarpeta"
	File Readme.txt
	File Agenda.jar
	File /r "reportes"
	File /r "img"
	File /r "config"
	File "icon.ico"
	#File "db.properties"
	#File "addcicon.png"
	#File "addpicon.png"
	#File "addticon.png"
	#File "ticon.png"
	#File "dbicon.png"
	#File "icon.png"

	#aca ejecutamos los instaladores de jre y mysql el /s es para la instalacion silenciosa
	setOutPath $TEMP
	File /r "Dependencias"
	#File "mysql-installer-community-5.6.20.0.msi"
	#File "jre-8u144-windows-i586.exe"
	#File "Connector.bat"	
	#File "Server.bat"	
	#File "GrantPrivileges.bat"
	
	ExecWait '"$TEMP\Dependencias\jre-8u144-windows-i586.exe" /s'
	#ExecWait 'msiexec /i "$TEMP\Dependencias\mysql-installer-community-5.6.20.0.msi" /quiet'
	#ExecWait '"$TEMP\Dependencias\Connector.bat"'
	#ExecWait '"$TEMP\Dependencias\Server.bat"'
	ExecWait '"$TEMP\Dependencias\GrantPrivileges.bat"'
	


	setOutPath $INSTDIR
	#crearAccesos:
	createShortCut "$DESKTOP\Agenda.lnk" "$INSTDIR\Agenda.jar" "$INSTDIR" "$INSTDIR\icon.ico" #

	#Con esto se informa el tamaño de la 	createShortCut "$DESKTOP" -jar $\"$INSTDIR\programa.jar" "" "$INSTDIR\icono.ico" #instalacion
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${NOMBRE}" "EstimatedSize" ${INSTALLSIZE}
	
SectionEnd

Function .onInit
  !ifdef IsSilent
    SetSilent silent
  !endif
FunctionEnd