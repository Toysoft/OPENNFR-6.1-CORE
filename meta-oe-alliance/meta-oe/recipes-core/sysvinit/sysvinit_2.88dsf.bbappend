FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR .= ".3"

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRC_URI += " \
    file://67_init_hddown.dpatch \
    file://92_sata-hddown.dpatch \
    ${@bb.utils.contains("MACHINE", "inihdp", "file://rc-inihdp", "", d)} \
    ${@bb.utils.contains("MACHINE", "inihde", "file://rc-inihde", "", d)} \
    ${@bb.utils.contains("MACHINE", "ew7362", "file://rc-ew7362", "", d)} \
    ${@bb.utils.contains("MACHINE", "inihde2", "file://rc-inihde2", "", d)} \
    ${@bb.utils.contains("MACHINE", "formuler1", "file://rc-formuler1", "", d)} \
    ${@bb.utils.contains("MACHINE", "spark7162", "file://rc-spark7162", "", d)} \
    ${@bb.utils.contains("MACHINE", "gb7358", "file://rc-gb800seplus", "", d)} \
    ${@bb.utils.contains("MACHINE", "vg5000", "file://rc-sf108", "", d)} \
    ${@bb.utils.contains("MACHINE", "g300", "file://rc-sf3038", "", d)} \
"

do_install_append() {
if [ "${MACHINE}" = "inihdp" ]; then
    mv ${WORKDIR}/rc-inihdp ${D}${sysconfdir}/init.d/rc
elif [ "${MACHINE}" = "inihde" ]; then
    mv ${WORKDIR}/rc-inihde ${D}${sysconfdir}/init.d/rc
elif [ "${MACHINE}" = "ew7362" ]; then
    mv ${WORKDIR}/rc-ew7362 ${D}${sysconfdir}/init.d/rc
elif [ "${MACHINE}" = "inihde2" ]; then
    mv ${WORKDIR}/rc-inihde2 ${D}${sysconfdir}/init.d/rc
elif [ "${MACHINE}" = "formuler1" ]; then
    mv ${WORKDIR}/rc-formuler1 ${D}${sysconfdir}/init.d/rc
elif [ "${MACHINE}" = "spark7162" ]; then
    mv ${WORKDIR}/rc-spark7162 ${D}${sysconfdir}/init.d/rc
elif [ "${MACHINE}" = "gb7358" ]; then
    mv ${WORKDIR}/rc-gb800seplus ${D}${sysconfdir}/init.d/rc
elif [ "${MACHINE}" = "vg5000" ]; then
    mv ${WORKDIR}/rc-sf108 ${D}${sysconfdir}/init.d/rc
elif [ "${MACHINE}" = "g300" ]; then
    mv ${WORKDIR}/rc-sf3038 ${D}${sysconfdir}/init.d/rc
fi
    rm ${D}${sysconfdir}/rc*.d/*bootlogd
}

do_install_append_spark7162() {
    # AOTOM rtc needs to be in localtime or standby time display will be wrong.
    sed -i -e '/^UTC=yes/{
s/^/# /;
a# *** aotom RTC on SPARK needs hwclock in localtime ***
aUTC=no
}' ${D}${sysconfdir}/default/rcS
}
