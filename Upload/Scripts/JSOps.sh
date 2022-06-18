#!/bin/sh
#############################################################
###  CREATING DIRECTORIES FOR FILE AND FOLDER OPERATIONS  ###
#############################################################

mkdir JobsModule
mkdir JobsModuleFolder
mkdir JobsModuleFolder/JobsModuleFolderOps
mkdir JobsModuleFolder/JobsModuleFolderOpsIcons
#########################################################
###  CREATING FILES FOR FILE OPERATIONS - JOBS PAGE   ###
#########################################################
cd JobsModule
touch ToRename.txt
echo "ToRename.txt ">>ToRename.txt
touch ToCompress_LV.txt
echo "ToCompress_LV.txt">>ToCompress_LV.txt
touch ToCopy.txt
echo "ToCopy.txt">>ToCopy.txt
touch ToCut.txt
echo "ToCut.txt">>ToCut.txt
touch ToDelete.txt
echo "ToDelete.txt">>ToDelete.txt
touch ToDownload.txt
echo "ToDownload.txt">>ToDownload.txt
touch ToCopy_Icon.txt
echo "ToCopy_Icon.txt">>ToCopy_Icon.txt
touch ToCut_Icon.txt
echo "ToCut_Icon.txt" >> ToCut_Icon.txt
touch ToDelete_Icon.txt
echo "ToDelete_Icon.txt">>ToDelete_Icon.txt
touch ToDownload_Icon.txt
echo "ToDownload_Icon.txt">>ToDownload_Icon.txt
touch ToCompress_LV_Icon.txt
echo "ToCompress_LV_Icon.txt" >>ToCompress_LV_Icon.txt
mkdir ToPaste
cd ../

########################################################################
### CREATING FOLDER FOR FOLDER OPERATIONS - CONTEXT MENU - JOBS PAGE ###
########################################################################

cd JobsModuleFolder/JobsModuleFolderOps
mkdir MyFolderCopy
mkdir MyFolderCut
mkdir MyFolderDelete
mkdir MyFolderDownload
mkdir MyFolderRename
mkdir ToPaste
cd ../


########################################################################
### CREATING FOLDER FOR FOLDER OPERATIONS - TOP MENU - JOBS PAGE     ###
########################################################################
cd JobsModuleFolderOpsIcons
mkdir MyFolderCopy
mkdir MyFolderCut
mkdir MyFolderDelete
mkdir MyFolderDownload
mkdir MyFolderRename
mkdir ToPaste
