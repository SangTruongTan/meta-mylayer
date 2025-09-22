DESCRIPTION = "Simple recipe that uses a dynamic C library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " file://mydynamicapp.c \
"

S = "${UNPACKDIR}"
B = "${WORKDIR}/build"

# Yocto will automatically put this package runtime library into RDEPENDS
# For more information, you can get the path PKGDATA_DIR from the bitbake -e <recipe_name>
# and use it to get the runtime information.
DEPENDS = "mydynamiclib"

do_compile() {
    bbdebug 1 "Do the compilation"
    ${CC} ${CFLAGS} ${S}/mydynamicapp.c -o ${B}/mydynamicapp \
        -lmydynamic \
        ${LDFLAGS} 
    bbdebug 1 "Compilation done"
}

do_install() {
    bbdebug 1 "Do the installation"
    install -d ${D}${bindir}/
    install -m 0755 ${B}/mydynamicapp ${D}${bindir}/
    bbdebug 1 "Installation done"
}

# ALLOW_EMPTY:${PN} = "1"


