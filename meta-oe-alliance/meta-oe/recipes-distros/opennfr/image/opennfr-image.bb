DESCRIPTION = "OPENNFR Image"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "OPENNFR team"

require conf/license/license-gplv2.inc

PV = "${IMAGE_VERSION}"
PR = "${BUILD_VERSION}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_rootfs[deptask] = "do_rm_work"

IMAGE_INSTALL = "opennfr-base \
    ${@bb.utils.contains("MACHINE_FEATURES", "dvbc-only", "", "enigma2-plugin-settings-defaultsat", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "singlecore", "", \
    " \
    packagegroup-base-smbfs-client \
    packagegroup-base-smbfs-server \
    packagegroup-base-smbfs-utils \
    packagegroup-base-nfs \
    ", d)} \
    "
    
export IMAGE_BASENAME = "opennfr-image"
IMAGE_LINGUAS = ""

IMAGE_FEATURES += "package-management"	

inherit image

image_preprocess() {
			curdir=$PWD
			cd ${IMAGE_ROOTFS}

			# because we're so used to it
			ln -s opkg usr/bin/ipkg || true
			ln -s opkg-cl usr/bin/ipkg-cl || true
			cd $curdir

			cd ${IMAGE_ROOTFS}/media
			mkdir -p ${IMAGE_ROOTFS}/media/card
			mkdir -p ${IMAGE_ROOTFS}/media/cf
			mkdir -p ${IMAGE_ROOTFS}/media/hdd
			mkdir -p ${IMAGE_ROOTFS}/media/net
			mkdir -p ${IMAGE_ROOTFS}/media/upnp
			mkdir -p ${IMAGE_ROOTFS}/media/usb
			mkdir -p ${IMAGE_ROOTFS}/media/usb1
			mkdir -p ${IMAGE_ROOTFS}/media/usb2
			mkdir -p ${IMAGE_ROOTFS}/media/usb3
			touch ${IMAGE_ROOTFS}/media/hdd/.fstab
			touch ${IMAGE_ROOTFS}/media/usb/.fstab
			touch ${IMAGE_ROOTFS}/media/usb1/.fstab
			touch ${IMAGE_ROOTFS}/media/net/.fstab
			cd $curdir
			
			cd ${IMAGE_ROOTFS}/etc
			rm -rf ${IMAGE_ROOTFS}/etc/passwd
			rm -rf ${IMAGE_ROOTFS}/etc/shadow
			mv ${IMAGE_ROOTFS}/etc/passwd-neu ${IMAGE_ROOTFS}/etc/passwd
			mv ${IMAGE_ROOTFS}/etc/shadow-neu ${IMAGE_ROOTFS}/etc/shadow
			rm -rf ${IMAGE_ROOTFS}/etc/passwd-neu
			rm -rf ${IMAGE_ROOTFS}/etc/shadow-neu
			mv ${IMAGE_ROOTFS}/etc/pwd.lock ${IMAGE_ROOTFS}/etc/.pwd.lock
			cd $curdir


			# cd ${IMAGE_ROOTFS}/var/lib/opkg/lists
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-3rdparty
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-all
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-${MACHINE}
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-${MACHINE}_3rdparty
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-mips32el
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-${MACHINEBUILD}
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-sh4
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-spark7162
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-spark7162_3rdparty
			# rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists/oe-sparktriplex
			# cd $curdir
			
			cd ${IMAGE_ROOTFS}/etc/network	
			rm -rf ${IMAGE_ROOTFS}/etc/network/interfaces
			mv ${IMAGE_ROOTFS}/etc/network/interfaces-neu ${IMAGE_ROOTFS}/etc/network/interfaces
			rm -rf ${IMAGE_ROOTFS}/etc/network/interfaces-neu
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES		
			rm -rf ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES/enigma2.mo
			mv ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES/enigma2-neu.mo ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES/enigma2.mo
			rm -rf ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES/enigma2-neu.mo
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/lib
			ln -s ${IMAGE_ROOTFS}/usr/lib/libbz2.so.0.0.0 ${IMAGE_ROOTFS}/usr/lib/libbz2.so.1.0 || true
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/emu
			tar xvpzf ${IMAGE_ROOTFS}/usr/emu/CCcam230.tar.gz -C ${IMAGE_ROOTFS}/usr/emu/
			tar xvpzf ${IMAGE_ROOTFS}/usr/emu/oscam.tar.gz -C ${IMAGE_ROOTFS}/usr/emu/
			rm -rf ${IMAGE_ROOTFS}/usr/emu/oscam.tar.gz
			rm -rf ${IMAGE_ROOTFS}/usr/emu/CCcam230.tar.gz
			cd $curdir

			cd ${IMAGE_ROOTFS}/var
			mkdir ${IMAGE_ROOTFS}/var/volatile/run
			cp ${IMAGE_ROOTFS}/var/smbd.pid ${IMAGE_ROOTFS}/var/volatile/run/smbd.pid
			cp ${IMAGE_ROOTFS}/var/nmbd.pid ${IMAGE_ROOTFS}/var/volatile/run/nmbd.pid
			rm -rf ${IMAGE_ROOTFS}/var/smbd.pid
			rm -rf ${IMAGE_ROOTFS}/var/nmbd.pid
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter
				tar xvpzf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so-mips.tar.gz -C ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/ 
			        mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so-mips ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so
				rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so-mips.tar.gz
				rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so-mips	
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data
				tar xvpzf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/unrar-free.tar.gz -C ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/
				tar xvpzf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/unzip.tar.gz -C ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/
				rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/unrar-free.tar.gz
				rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/unzip.tar.gz		
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin
				tar xvpzf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin/nfr4xbm.tar.gz -C ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin
				tar xvpzf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin/fbclear.tar.gz -C ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin
				tar xvpzf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/ubi_reader/ubifs/lzo.so.tar.gz -C ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/ubi_reader/ubifs
				rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin/nfr4xbm.tar.gz
				rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin/fbclear.tar.gz
				rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/ubi_reader/ubifs/lzo.so.tar.gz	
			cd $curdir
			
	
    # Speedup boot by reducing the host key size. The time it takes grows
    # exponentially by key size, the default is 2k which takes several
    # seconds on most boxes.
    echo 'DROPBEAR_RSAKEY_ARGS="-s 1024"' >> ${IMAGE_ROOTFS}${sysconfdir}/default/dropbear		
}

IMAGE_PREPROCESS_COMMAND += "image_preprocess; "
