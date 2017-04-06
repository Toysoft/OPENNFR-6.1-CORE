DESCRIPTION = "OpenNFR Cam files"
LICENSE = "GPL2"

require conf/license/license-gplv2.inc

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

SRC_URI = "file://*"

FILES_${PN} = "/var* /usr/bin/* /usr/lib/python2.7/* /usr/lib/python2.7/site-packages/twisted/web/* /usr/emu/* /usr/keys/* /usr/share/enigma2/defaults/* /usr/share/enigma2/* /usr/share/enigma2/rc_models/ini4/* /usr/share/enigma2/rc_models/red2/* /usr/share/enigma2/po/de/LC_MESSAGES/* /usr/lib/enigma2/python/Components/Converter/* /usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/* /usr/lib/enigma2/python/Plugins/Extensions/Infopanel/* /usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin/* /usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/ubi_reader/ubifs/* /usr/scripts"
S = "${WORKDIR}"

do_install() {
	    
    install -d ${D}/usr/lib/enigma2/python/Components/Converter
    for f in bitratecalc.so-mips.tar.gz bitratecalc.so-sh4.tar.gz
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Components/Converter/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data
    for f in iperf.tar.gz unrar-free.tar.gz unzip.tar.gz
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin
    for f in nfr4xbm.tar.gz fbclear.tar.gz
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/bin/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/ubi_reader/ubifs
    for f in lzo.so.tar.gz
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot/ubi_reader/ubifs/${f}
    done


    install -d ${D}/var
    for f in smbd.pid nmbd.pid
    do
        install -m 755 ${f} ${D}/var/${f}
    done

    install -d ${D}/usr/lib/python2.7/site-packages/twisted/web
    for f in client-neu.py
    do
        install -m 755 ${f} ${D}/usr/lib/python2.7/site-packages/twisted/web/${f}
    done

    install -d ${D}/usr/emu
    for f in CCcam230.tar.gz oscam.tar.gz
    do
        install -m 755 ${f} ${D}/usr/emu/${f}
    done

    install -d ${D}/usr/keys
    for f in CCcam.cfg SoftCam.Key oscam.conf oscam.keys oscam.provid oscam.server oscam.services oscam.srvid oscam.user softcam.cfg oscam.dvbapi oscam.ccache
    do
        install -m 644 ${f} ${D}/usr/keys/${f}
    done


    install -d ${D}/usr/share/enigma2/defaults
    for f in bouquets* userbouquet* autotimer.xml cert.pem key.pem lamedb lamedb5 menusort.xml pluginsort.xml subservices.xml blacklist whitelist
    do
        install -m 644 ${f} ${D}/usr/share/enigma2/defaults/${f}
    done
    
   if [ "${MACHINE}" = "inihdp" ]; then
   	install -d ${D}/usr/share/enigma2/rc_models/ini4
    	for f in rc-neu.png
    	do
	    install -m 644 ${f} ${D}/usr/share/enigma2/rc_models/ini4/${f}
	done
   else
            echo "all ok"
    fi
 
    if [ "${MACHINE}" = "inihde" ]; then
   	install -d ${D}/usr/share/enigma2/rc_models/ini4
    	for f in rc-neu.png
    	do
	    install -m 644 ${f} ${D}/usr/share/enigma2/rc_models/ini4/${f}
	done
   else
            echo "all ok"
    fi
    
   if [ "${MACHINE}" = "7200s" ]; then
   	install -d ${D}/usr/share/enigma2/rc_models/red2
    	for f in rc.png rcpositions.xml remote.html
    	do
       	    install -m 644 ${f} ${D}/usr/share/enigma2/rc_models/red2/${f}
    	done
   else
            echo "all ok"
    fi

   install -d ${D}/usr/share/enigma2/po/de/LC_MESSAGES
    for f in enigma2-neu.mo
    do
        install -m 644 ${f} ${D}/usr/share/enigma2/po/de/LC_MESSAGES/${f}
    done

}
do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
