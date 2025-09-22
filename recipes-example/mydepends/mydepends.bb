DESCRIPTION = "Simple recipe that uses a static C library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " file://mydepends.c \
"

S = "${UNPACKDIR}"
B = "${WORKDIR}/build"

DEPENDS = "mystaticlib"

do_compile() {
    bbdebug 1 "Do the compilation"
    ${CC} ${CFLAGS} ${S}/mydepends.c -o ${B}/mydepends \
        -l:mylib.a \
        ${LDFLAGS} 
    bbdebug 1 "Compilation done"
}

do_install() {
    bbdebug 1 "Do the installation"
    install -d ${D}${bindir}/
    install -m 0755 ${B}/mydepends ${D}${bindir}/
    bbdebug 1 "Installation done"
}

# ALLOW_EMPTY:${PN} = "1"


