DESCRIPTION = "Simple helloworld C application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://userprog.c"

S = "${UNPACKDIR}"

# FILES:${PN} += "${bindir}"

do_compile() {
    ${CC} ${CFLAGS} userprog.c -o ${B}/userprog ${LDFLAGS}
}

do_install() {
    bbnote "[SANGT] Hello my worlds"
    bbplain "Hello wold. This is bbplain"
    bbwarn "Installation is in progress"
    install -d ${D}${bindir}
    install -m 0755 ${B}/userprog ${D}${bindir}
    bbnote "Installation complete"
    bbdebug 1 "Installation is completed"
    bbdebug 2 "Installation is completed again"
    bbdebug 3 "Installation is completed again again"
}

