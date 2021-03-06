DESCRIPTION = "Builds eDMA module used by eDMA libraries for PRU sw example applications"
HOMEPAGE = "https://gforge.ti.com/gf/project/pru_sw/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://edmautils.c;beginline=1;endline=23;md5=312e9cb8a37a044c617c98a9e980ad1b"

COMPATIBLE_MACHINE = "omapl138"

INHIBIT_PACKAGE_STRIP = "1"

MACHINE_KERNEL_PR:append = "b"
PR = "${MACHINE_KERNEL_PR}"
PV:append = "+svn${SRCPV}"

SRC_URI = "svn://gforge.ti.com/svn/pru_sw/;module=trunk;protocol=https;user=anonymous;pswd=''"

SRCREV = "33"

S = "${WORKDIR}/trunk/peripheral_lib/edma_driver/module"

inherit module

EXTRA_OEMAKE += "KERNEL_DIR='${STAGING_KERNEL_DIR}'"

do_compile:prepend () {
        export CCTOOL_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}"
}

do_install () {
        install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/pru
        install -m 0755 ${S}/edmautils.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/pru/
}
