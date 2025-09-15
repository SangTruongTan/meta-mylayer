DESCRIPTION = "Simple helloworld C application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://userprog.c"

S = "${WORKDIR}"

FILES:${PN} += "${bindir}"

do_compile() {
    ${CC} ${CFLAGS} userprog.c -o ${B}/userprog ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/userprog ${D}${bindir}
}

