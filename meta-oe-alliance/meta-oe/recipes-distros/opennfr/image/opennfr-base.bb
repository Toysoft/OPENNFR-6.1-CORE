SUMMARY = "Base packages require for image."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
                    
ALLOW_EMPTY_${PN} = "1"

PV = "${IMAGE_VERSION}"
PR = "r1"

inherit packagegroup

RDEPENDS_${PN} = "\
    ca-certificates \
    oe-alliance-base \
    opennfr-enigma2 \
    opennfr-bootlogo \
    opennfr-version-info \
    opennfr-cam \
    opennfr-settings \    
    openssh-sftp-server \
    ${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "", "iproute2 ", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "", "ntfs-3g ", d)} \  
    hddtemp \
    busybox-cron \
    python-imaging \
    python-cfscrape \
    python-js2py \
    python-requests \
    python-ipaddress  \
    python-netifaces \
    python-pysnmp-se \
    ofgwrite \
    libcrypto-compat-0.9.8 \
    python-gdata \
    libshowiframe \
    dvbsnoop \
    librtmp \
    rtmpdump \
    iperf3 \
    mjpegtools \
    packagegroup-base-smbfs-client \
    packagegroup-base-smbfs-server \
    packagegroup-base-smbfs-utils \
    packagegroup-base-nfs \
    enigma2-plugin-drivers-usbserial \
    enigma2-plugin-extensions-openwebif-terminal \
    enigma2-plugin-extensions-openwebif-themes \
    enigma2-plugin-extensions-openwebif-webtv \
    enigma2-plugin-extensions-openwebif-vxg \
    enigma2-plugin-systemplugins-serviceapp \       
    bash \
    ${@bb.utils.contains("MACHINE_FEATURES", "dreamboxv1", "", "ofgwrite", d)} \
    ${@bb.utils.contains("TUNE_FEATURES", "armv", "glibc-compat", "", d)} \
    "
