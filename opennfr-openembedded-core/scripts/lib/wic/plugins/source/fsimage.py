# ex:ts=4:sw=4:sts=4:et
# -*- tab-width: 4; c-basic-offset: 4; indent-tabs-mode: nil -*-
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License version 2 as
# published by the Free Software Foundation.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License along
# with this program; if not, write to the Free Software Foundation, Inc.,
# 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
#

import logging
import os

from wic import WicError
from wic.pluginbase import SourcePlugin
from wic.utils.misc import get_bitbake_var

logger = logging.getLogger('wic')

class FSImagePlugin(SourcePlugin):
    """
    Add an already existing filesystem image to the partition layout.
    """

    name = 'fsimage'

    @classmethod
    def do_prepare_partition(cls, part, source_params, cr, cr_workdir,
                             oe_builddir, bootimg_dir, kernel_dir,
                             rootfs_dir, native_sysroot):
        """
        Called to do the actual content population for a partition i.e. it
        'prepares' the partition to be incorporated into the image.
        """
        if not bootimg_dir:
            bootimg_dir = get_bitbake_var("DEPLOY_DIR_IMAGE")
            if not bootimg_dir:
                raise WicError("Couldn't find DEPLOY_DIR_IMAGE, exiting")

        logger.debug('Bootimg dir: %s', bootimg_dir)

        if 'file' not in source_params:
            raise WicError("No file specified")

        src = os.path.join(bootimg_dir, source_params['file'])


        logger.debug('Preparing partition using image %s', src)
        part.prepare_rootfs_from_fs_image(cr_workdir, src, "")
