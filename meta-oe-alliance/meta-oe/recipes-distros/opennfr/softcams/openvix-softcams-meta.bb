SUMMARY = "meta file for enigma2 softcam packages"

require conf/license/license-gplv2.inc

PROVIDES = "openvix-softcams"

DEPENDS = "\
    ${@bb.utils.contains("TARGET_ARCH", "mipsel", "openvix-softcams-cccam", "", d)} \
    ${@bb.utils.contains("TARGET_ARCH", "mipsel", "openvix-softcams-evocamd", "", d)} \
    ${@bb.utils.contains("TARGET_ARCH", "mipsel", "openvix-softcams-mgcamd138", "", d)} \
    ${@bb.utils.contains("TARGET_ARCH", "mipsel", "openvix-softcams-oscam-latest-mipsel" , "", d)} \
    ${@bb.utils.contains("TARGET_ARCH", "mipsel", "openvix-softcams-rqcamd", "", d)} \
    ${@bb.utils.contains("TARGET_ARCH", "mipsel", "openvix-softcams-scam", "", d)} \
    "    

PR = "r11"
