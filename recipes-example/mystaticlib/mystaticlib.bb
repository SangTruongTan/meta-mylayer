DESCRIPTION = "Simple static C library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " file://arith.c \
            file://mylib.h \
            file://print.c \
"

S = "${UNPACKDIR}"

do_compile() {
    bbdebug 1 "Do the compilation"
    ${CC} ${CFLAGS} -c arith.c -I. ${LDFLAGS}
    ${CC} ${CFLAGS} -c print.c -I. ${LDFLAGS}
    ${AR} crs mylib.a arith.o print.o
    bbdebug 1 "Compilation done"
}

do_install() {
    bbdebug 1 "Do the installation"
    install -d ${D}${libdir}
    install -m 0755 mylib.a ${D}${libdir}
    install -d ${D}${includedir}
    install -m 0644 mylib.h ${D}${includedir}
    bbdebug 1 "Installation done"
}

# ALLOW_EMPTY:${PN} = "1"


