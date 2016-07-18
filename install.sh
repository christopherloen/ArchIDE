#!/bin/bash
mkdir ~/.archide
cp ArchIDE.jar ~/.archide/
cp archide.conf ~/.archide/
cp upload.png ~/.archide/
echo "#!/bin/bash" >> archide
echo "cd ~/.archide/ && java -jar ArchIDE.jar" >> archide
chmod +x archide
echo -e "Moving the executable to /usr/bin ..."
sudo cp archide /usr/bin/archide
echo -e "\nDone, enter 'archide' to run ArchIDE"
