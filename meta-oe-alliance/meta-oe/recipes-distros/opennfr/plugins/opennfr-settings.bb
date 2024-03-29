DESCRIPTION = "OpenNFR Settings files"
LICENSE = "GPL2"

require conf/license/license-gplv2.inc

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

SRC_URI = "file://*"

FILES_${PN} = "/etc/enigma2/* /etc/tuxbox/* /etc/* /etc/init.d/* /etc/samba/* /etc/network/* "
S = "${WORKDIR}"

do_install() {
    install -d ${D}/etc/enigma2
    for f in mp_pluginliste bouquets* userbouquet* autotimer.xml cert.pem key.pem lamedb lamedb5 menusort.xml subservices.xml pluginsort.xml settings lcndb epgimport.conf blacklist whitelist
    do
        install -m 644 ${f} ${D}/etc/enigma2/${f}
    done

    install -d ${D}/etc/tuxbox
    for f in satellites.xml terrestrial.xml timezone.xml cables.xml
    do
        install -m 644 ${f} ${D}/etc/tuxbox/${f}
    done

    install -d ${D}/etc
    for f in wpa_supplicant.ra0.conf wpa_supplicant.wlan0.conf pwd.lock passwd-neu shadow-neu emulist
    do
        install -m 644 ${f} ${D}/etc/${f}
    done

    install -d ${D}/etc/opkg
    for f in secret-feed.conf secret-feed-arm.conf
    do
        install -m 644 ${f} ${D}/etc/opkg/${f}
    done

    install -d ${D}/etc/init.d
    for f in swap
    do
        install -m 755 ${f} ${D}/etc/init.d/${f}
    done	
	
    install -d ${D}/etc/network
    for f in interfaces-neu
    do
        install -m 755 ${f} ${D}/etc/network/${f}
    done

}

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
