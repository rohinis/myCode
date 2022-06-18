#!/bin/sh
#cd /stage/$USER/
#############################################################
###  CREATING DIRECTORIES FOR FILE AND FOLDER OPERATIONS  ###
#############################################################

mkdir FoldersModule
mkdir FoldersModule/FolderOpsContextMenu
mkdir FoldersModule/FolderOpsIcons
mkdir JobsModuleFolder
mkdir JobsModuleFolder/JobsModuleFolderOps
mkdir JobsModuleFolder/JobsModuleFolderOpsIcons
#########################################################################
###  CREATING FOLDER FOR FOLDER OPERATIONS - CONTEXT MENU - FILES TAB ###
#########################################################################

cd FoldersModule/FolderOpsContextMenu
mkdir MyFolderCompress_LV
mkdir MyFolderCompress_TV
mkdir MyFolderCopy_TV
mkdir MyFolderCopy_LV
mkdir MyFolderCut_TV
mkdir MyFolderCut_LV
mkdir MyFolderDelete_TV
mkdir MyFolderDelete_LV
mkdir MyFolderDownload_LV
mkdir MyFolderDownload_TV
mkdir MyFolderRename_TV
mkdir MyFolderRename_LV
mkdir MyFolderUpload_TV
mkdir ToPaste

########################################################################
###  CREATING FOLDER FOR FOLDER OPERATIONS - TOP ICON - FILES TAB    ###
########################################################################

cd ..
cd FolderOpsIcons
mkdir MyFolderCompress_LV
mkdir MyFolderCompress_TV
mkdir MyFolderCopy_TV
mkdir MyFolderCopy_LV
mkdir MyFolderCut_TV
mkdir MyFolderCut_LV
mkdir MyFolderDelete_TV
mkdir MyFolderDelete_LV
mkdir MyFolderDownload_LV
mkdir MyFolderDownload_TV
mkdir MyFolderRename_TV
mkdir MyFolderRename_LV
mkdir MyFolderUpload_TV
mkdir ToPaste
cd ../../


########################################################################
### CREATING FOLDER FOR FOLDER OPERATIONS - CONTEXT MENU - JOBS PAGE ###
########################################################################

cd JobsModuleFolder/JobsModuleFolderOps
mkdir MyFolderCopy
mkdir MyFolderCut
mkdir MyFolderDelete
mkdir MyFolderDownload
mkdir MyFolderRename_LV
mkdir ToPaste



########################################################################
### CREATING FOLDER FOR FOLDER OPERATIONS - TOP MENU - JOBS PAGE     ###
########################################################################
cd ..
cd JobsModuleFolderOpsIcons
mkdir MyFolderCopy
mkdir MyFolderCut
mkdir MyFolderDelete
mkdir MyFolderDownload
mkdir MyFolderRename_LV
mkdir ToPaste