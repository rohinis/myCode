#!/bin/sh
#cd /stage/$USER/
#############################################################
###  CREATING DIRECTORIES FOR FILE AND FOLDER OPERATIONS  ###
#############################################################

mkdir FilesModule
mkdir JobsModule
mkdir FilesModule/FileOps
mkdir FilesModule/FileOpsIcons
mkdir FilesModule/FolderOpsContextMenu
mkdir FilesModule/FolderOpsIcons


########################################################################
###  CREATING FILES FOR FILE OPERATIONS - CONTEXT MENU - FILES TAB   ###
########################################################################


cd FilesModule/FileOps
touch ToCompress_LV.txt  
echo " Compress list view  ">> ToCompress_LV.txt
touch ToCompress_TV.txt  
echo " Compress tile view  ">> ToCompress_TV.txt
touch ToCopy_LV.txt  
echo " Copy list view ">> ToCopy_LV.txt
touch ToCopy_TV.txt  
echo " Copy tile view ">> ToCopy_TV.txt
touch ToCut_LV.txt  
echo " Cut list view ">> ToCut_LV.txt
touch ToCut_TV.txt  
echo " Cut tile view ">> ToCut_TV.txt
touch ToDelete_LV.txt  
echo " Delete list view ">> ToDelete_LV.txt
touch ToDelete_TV.txt  
echo " Delete tile view ">> ToDelete_TV.txt
touch ToDownload_LV.txt 
echo " Download File ">> ToDownload_LV.txt
touch ToDownload_TV.txt 
echo " Download File ">> ToDownload_TV.txt
touch ToRename_ListView.txt  
echo " Rename list view  ">> ToRename_ListView.txt
touch ToRename_TileView.txt  
echo " Rename tile view  ">> ToRename_TileView.txt
touch ToUpload.txt  
echo " Upload File ">> ToUpload.txt
mkdir ToPaste

########################################################################
###  CREATING FILES FOR FILE OPERATIONS - TOP ICON - FILES TAB       ###
########################################################################
cd ..
cd FileOpsIcons
touch ToCompress_LV.txt  
echo " Compress list view  ">> ToCompress_LV.txt
touch ToCompress_TV.txt  
echo " Compress tile view  ">> ToCompress_TV.txt
touch ToCopy_LV.txt  
echo " Copy list view ">> ToCopy_LV.txt
touch ToCopy_TV.txt  
echo " Copy tile view ">> ToCopy_TV.txt
touch ToCut_LV.txt  
echo " Cut list view ">> ToCut_LV.txt
touch ToCut_TV.txt  
echo " Cut tile view ">> ToCut_TV.txt
touch ToDelete_LV.txt  
echo " Delete list view ">> ToDelete_LV.txt
touch ToDelete_TV.txt  
echo " Delete tile view ">> ToDelete_TV.txt
touch ToDownload_LV.txt 
echo " Download File ">> ToDownload_LV.txt
touch ToDownload_TV.txt 
echo " Download File ">> ToDownload_TV.txt
touch ToRename_ListView.txt  
echo " Rename list view  ">> ToRename_ListView.txt
touch ToRename_TileView.txt  
echo " Rename tile view  ">> ToRename_TileView.txt
touch ToUpload.txt  
echo " Upload File ">> ToUpload.txt
mkdir ToPaste
########################################################################
###  CREATING FOLDER FOR FOLDER OPERATIONS - CONTEXT MENU - FILES TAB ###
########################################################################
cd ..
cd FolderOpsContextMenu
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

#########################################################
###  CREATING FILES FOR FILE OPERATIONS - JOBS PAGE   ###
#########################################################
cd ../..
cd JobsModule
touch ToRename_ListView.txt
echo "ToRename_ListView.txt ">>ToRename_ListView.txt
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

