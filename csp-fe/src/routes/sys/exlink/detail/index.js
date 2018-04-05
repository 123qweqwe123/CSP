import React from 'react'
import PropTypes from 'prop-types'
import Iframe from 'react-iframe'
import { connect } from 'dva'
import { Page } from 'components'

// TODO 修改了 iframe 插件的源码（原插件检测到了我有 height 这个属性，不知道为啥依然自定义了 style）
const ExlinkDetail = ({
  // match,
  exlinkdetail,
}) => {
  const {
    width, height, url, position, allowFullScreen,
  } = exlinkdetail
  return (
    <Page inner>
      {/* {match.params.reportId} */}
      <Iframe url={url}
        display="initial"
        height={height}
        witdh={width}
        position={position}
        styles={{ height }}
        allowFullScreen={allowFullScreen === 1}
      />
    </Page>
  )
}

ExlinkDetail.propTypes = {
  exlinkdetail: PropTypes.object,
}

export default connect(({ loading, exlinkdetail }) => ({ loading, exlinkdetail }))(ExlinkDetail)
