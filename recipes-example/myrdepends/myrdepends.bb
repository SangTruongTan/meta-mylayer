DESCRIPTION = "Simple recipe that requires a library to be present at runtime"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " file://myrdepends.c \
"

S = "${UNPACKDIR}"
B = "${WORKDIR}/build"

RDEPENDS:${PN} += "pciutils"

do_compile() {
    bbdebug 1 "Do the compilation"
    ${CC} ${CFLAGS} ${S}/myrdepends.c -o ${B}/myrdepends \
        ${LDFLAGS} 
    bbdebug 1 "Compilation done"
}

do_install() {
    bbdebug 1 "Do the installation"
    install -d ${D}${bindir}/
    install -m 0755 ${B}/myrdepends ${D}${bindir}/
    bbdebug 1 "Installation done"
}

