DESCRIPTION = "OpenNFR bootlogo"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "opennfr"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require conf/license/license-gplv2.inc

RDEPENDS_${PN} += "showiframe"

PV = "${IMAGE_VERSION}"
PR = "r0"

S = "${WORKDIR}"

INITSCRIPT_NAME = "bootlogo"
INITSCRIPT_PARAMS = "start 06 S ."
INITSCRIPT_PARAMS_vuduo2 = "start 70 S . stop 89 0 ."
INITSCRIPT_PARAMS_vusolo2 = "start 70 S . stop 89 0 ."
INITSCRIPT_PARAMS_vusolose = "start 70 S . stop 89 0 ."

inherit update-rc.d

SRC_URI = "file://bootlogo.mvi file://backdrop.mvi file://bootlogo_wait.mvi file://radio.mvi file://bootlogo.sh ${@bb.utils.contains("MACHINE_FEATURES", "bootsplash", "file://splash.bin" , "", d)} ${@bb.utils.contains("MACHINE_FEATURES", "bootsplash", "file://splash480.bin" , "", d)}"
SRC_URI_append_gb800ue = "file://lcdsplash.bin file://lcdwaitkey.bin file://lcdwarning.bin"
SRC_URI_append_gbquad = "file://lcdsplash.bin file://lcdwaitkey.bin file://lcdwarning.bin"
SRC_URI_append_gb800ueplus = "file://lcdsplash.bin file://lcdwaitkey.bin file://lcdwarning.bin"
SRC_URI_append_gbquadplus = "file://gbquadplus/lcdsplash400.bin file://gbquadplus/lcdwaitkey400.bin file://gbquadplus/lcdwarning400.bin"
SRC_URI_append_vuduo2 = "file://lcdbootlogo.png file://bootlogo.py"
SRC_URI_append_7100s = "file://7100s/lcdsplash220.bin file://7100s/lcdwaitkey220.bin file://7100s/lcdwarning220.bin file://7100s/lcdcomplete220.bin"
SRC_URI_append_inihdp = "file://inihdp/cfe.bmp file://inihdp/finished.bmp file://inihdp/imageversion.bmp file://inihdp/kernel.bmp file://inihdp/rootfs.bmp file://inihdp/splash.bmp"
SRC_URI_append_inihde2 = "file://inihdp/cfe.bmp file://inihdp/finished.bmp file://inihdp/imageversion.bmp file://inihdp/kernel.bmp file://inihdp/rootfs.bmp file://inihdp/splash.bmp"
SRC_URI_append_dags7356 = "file://splash1.bmp file://splash1_os1.bmp file://splash1_os2.bmp file://splash2.bmp file://splash3.bmp"
SRC_URI_append_dags7362 = "file://splash1_power.bmp file://splash1_os1.bmp file://splash1_os2.bmp file://splash2.bmp file://splash3.bmp"
SRC_URI_append_dags7362 = "file://splash1_power.bmp file://splash1_os1.bmp file://splash1_os2.bmp file://splash2.bmp file://splash3.bmp"

SRC_URI_append_gbultraue = "file://gbultraue/lcdsplash220.bin file://gbultraue/lcdwaitkey220.bin file://gbultraue/lcdwarning220.bin file://gbultraue/lcdcomplete220.bin"


BINARY_VERSION = "1.3"

SRC_URI += "${@bb.utils.contains("MACHINE_FEATURES", "dreambox", "http://dreamboxupdate.com/download/opendreambox/2.0.0/dreambox-bootlogo/dreambox-bootlogo_${BINARY_VERSION}_${MACHINE_ARCH}.tar.bz2;name=${MACHINE_ARCH}" , "", d)}"

SRC_URI[dm800.md5sum] = "0aacd07cc4d19b388c6441b007e3525a"
SRC_URI[dm800.sha256sum] = "978a7c50fd0c963013477b5ba08462b35597ea130ae428c828bfcbb5c7cf4cac"
SRC_URI[dm8000.md5sum] = "1b63ac7e2bd5c0db0748606acc310d47"
SRC_URI[dm8000.sha256sum] = "91e4402190fe88cf394ae780141d968a1ebecd8db7b23c1f0ca3f4bfa9c9512a"
SRC_URI[dm800se.md5sum] = "3413a894a3d77e02cae34b96d302817d"
SRC_URI[dm800se.sha256sum] = "8a283442c231e82ee1a2093e53dc5bf52c478e12d22c79af7e7026b52711fc9c"
SRC_URI[dm500hd.md5sum] = "b9ada70304ca1f9a8e36a55bd38834c6"
SRC_URI[dm500hd.sha256sum] = "d4b0f650711d5d6fdecb42efe9e13987ef803cba829d0950e899608a784ae3b2"
SRC_URI[dm7020hd.md5sum] = "f8e423dbf7661367659fa86a68b74bc4"
SRC_URI[dm7020hd.sha256sum] = "118d7bb57c4b41dd45c7bdd9a056a0745454f42092692fb4309997e035eb6908"
SRC_URI[dm800sev2.md5sum] = "a570f8f2eb4d7800a2fa2db60d81b58e"
SRC_URI[dm800sev2.sha256sum] = "af522a5d4dc75507f2cd96582a270236fedade35b8dca74c0f75d999ffb210bf"
SRC_URI[dm500hdv2.md5sum] = "c0413bfe6c03efc5fa1825b6ad8ac7bd"
SRC_URI[dm500hdv2.sha256sum] = "005b9e99566fdee4d76ec1532273dc3e29a14b723d0bf6108228988e2a30d013"

FILES_${PN} = "/boot /usr/share /etc/init.d"

do_install() {
    ${@bb.utils.contains("MACHINE_FEATURES", "dreambox", "install -d ${D}/boot", "", d)}
    ${@bb.utils.contains("MACHINE_FEATURES", "dreambox", "install -m 0755 ${S}/dreambox-bootlogo_${BINARY_VERSION}_${MACHINE_ARCH}/bootlogo-${MACHINE_ARCH}.elf.gz ${D}/boot/; install -m 0755 ${S}/dreambox-bootlogo_${BINARY_VERSION}_${MACHINE_ARCH}/bootlogo-${MACHINE_ARCH}.jpg ${D}/boot/", "", d)}
    install -d ${D}/usr/share
    install -m 0644 bootlogo.mvi ${D}/usr/share/bootlogo.mvi
    ln -sf /usr/share/bootlogo.mvi ${D}/usr/share/backdrop.mvi
    install -d ${D}/usr/share/enigma2
    install -m 0644 radio.mvi ${D}/usr/share/enigma2/radio.mvi
    install -d ${D}/${sysconfdir}/init.d
    install -m 0755 bootlogo.sh ${D}/${sysconfdir}/init.d/bootlogo
}
do_install_append_gb800ue() {
    install -d ${D}/usr/share
    install -m 0644 lcdwaitkey.bin ${D}/usr/share/lcdwaitkey.bin
    install -m 0644 lcdwarning.bin ${D}/usr/share/lcdwarning.bin
}

do_install_append_gbquad() {
    install -d ${D}/usr/share
    install -m 0644 lcdwaitkey.bin ${D}/usr/share/lcdwaitkey.bin
    install -m 0644 lcdwarning.bin ${D}/usr/share/lcdwarning.bin	
}

do_install_append_gb800ueplus() {
    install -d ${D}/usr/share
    install -m 0644 lcdwaitkey.bin ${D}/usr/share/lcdwaitkey.bin
    install -m 0644 lcdwarning.bin ${D}/usr/share/lcdwarning.bin	
}

do_install_append_gbquadplus() {
    install -m 0644 ${S}/gbquadplus/lcdsplash400.bin ${DEPLOY_DIR_IMAGE}/lcdsplash400.bin 
    install -m 0644 ${S}/gbquadplus/lcdwaitkey400.bin ${DEPLOY_DIR_IMAGE}/lcdwaitkey400.bin  
    install -m 0644 ${S}/gbquadplus/lcdwarning400.bin ${DEPLOY_DIR_IMAGE}/lcdwarning400.bin  
}

do_install_append_gb7362() {
    install -m 0644 ${S}/gbultraue/lcdsplash220.bin ${DEPLOY_DIR_IMAGE}/lcdsplash220.bin    
    install -m 0644 ${S}/gbultraue/lcdwaitkey220.bin ${DEPLOY_DIR_IMAGE}/lcdwaitkey220.bin  
    install -m 0644 ${S}/gbultraue/lcdwarning220.bin ${DEPLOY_DIR_IMAGE}/lcdwarning220.bin  
    install -m 0644 ${S}/gbultraue/lcdcomplete220.bin ${DEPLOY_DIR_IMAGE}/lcdcomplete220.bin 
}


do_install_append_vuduo2() {
    install -m 0644 lcdbootlogo.png ${D}/usr/share/lcdbootlogo.png
    install -m 0644 bootlogo.py ${D}/${sysconfdir}/init.d/bootlogo.py
}

do_install_append_7100s() {
    install -d ${D}/usr/share
    install -m 0644 ${S}/7100s/lcdsplash220.bin ${D}/usr/share/lcdsplash.bin
    install -m 0644 ${S}/7100s/lcdwaitkey220.bin ${D}/usr/share/lcdwaitkey.bin
    install -m 0644 ${S}/7100s/lcdwarning220.bin ${D}/usr/share/lcdwarning.bin
    install -m 0644 ${S}/7100s/lcdcomplete220.bin ${D}/usr/share/lcdcomplete.bin
    install -m 0644 ${S}/7100s/lcdsplash220.bin ${DEPLOY_DIR_IMAGE}/lcdsplash220.bin    
}

do_install_append_inihdp() {
    install -m 0644 ${S}/inihdp/cfe.bmp ${DEPLOY_DIR_IMAGE}/cfe.bmp
    install -m 0644 ${S}/inihdp/finished.bmp ${DEPLOY_DIR_IMAGE}/finished.bmp
    install -m 0644 ${S}/inihdp/imageversion.bmp ${DEPLOY_DIR_IMAGE}/imageversion.bmp
    install -m 0644 ${S}/inihdp/kernel.bmp ${DEPLOY_DIR_IMAGE}/kernel.bmp
    install -m 0644 ${S}/inihdp/rootfs.bmp ${DEPLOY_DIR_IMAGE}/rootfs.bmp   
    install -m 0644 ${S}/inihdp/splash.bmp ${DEPLOY_DIR_IMAGE}/splash.bmp  
}

do_install_append_inihde2() {
    install -m 0644 ${S}/inihdp/cfe.bmp ${DEPLOY_DIR_IMAGE}/cfe.bmp
    install -m 0644 ${S}/inihdp/finished.bmp ${DEPLOY_DIR_IMAGE}/finished.bmp
    install -m 0644 ${S}/inihdp/imageversion.bmp ${DEPLOY_DIR_IMAGE}/imageversion.bmp
    install -m 0644 ${S}/inihdp/kernel.bmp ${DEPLOY_DIR_IMAGE}/kernel.bmp
    install -m 0644 ${S}/inihdp/rootfs.bmp ${DEPLOY_DIR_IMAGE}/rootfs.bmp   
    install -m 0644 ${S}/inihdp/splash.bmp ${DEPLOY_DIR_IMAGE}/splash.bmp  
}

inherit deploy
do_deploy() {
    if [ "${BRAND_OEM}" = "vuplus" ] || [ "${BRAND_OEM}" = "dags" ]; then
	install -m 0644 splash480.bin ${DEPLOYDIR}/${BOOTLOGO_FILENAME}
    else
    	if [ -e splash.bin ]; then
        	install -m 0644 splash.bin ${DEPLOYDIR}/${BOOTLOGO_FILENAME}
    	fi
    fi
    if [ -e lcdsplash.bin ]; then
    install -m 0644 lcdsplash.bin ${DEPLOYDIR}/lcdsplash.bin
    fi
    if [ -e lcdsplash400.bin ]; then
        install -m 0644 lcdsplash400.bin ${DEPLOYDIR}/lcdsplash.bin
    fi

}

addtask deploy before do_build after do_install

pkg_preinst_${PN}() {
    if grep dm /etc/hostname > /dev/null ; then
        if [ -z "$D" ]
        then
            if mountpoint -q /boot
            then
                mount -o remount,rw,compr=none /boot
            else
                mount -t jffs2 -o rw,compr=none mtd:'boot partition' /boot
            fi
        fi
    fi
}

pkg_postinst_${PN}() {
    if grep dm /etc/hostname > /dev/null ; then
        if [ -z "$D" ]
        then
            umount /boot
        fi
    fi
}

pkg_prerm_${PN}() {
    if grep dm /etc/hostname > /dev/null ; then
        if [ -z "$D" ]
        then
            if mountpoint -q /boot
            then
                mount -o remount,rw,compr=none /boot
            else
                mount -t jffs2 -o rw,compr=none mtd:'boot partition' /boot
            fi
        fi
    fi
}

pkg_postrm_${PN}() {
    if grep dm /etc/hostname > /dev/null ; then
        if [ -z "$D" ]
        then
            umount /boot
        fi
    fi
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/boot /usr/share /etc/init.d"
