import React from 'react'
import ReactDOM from 'react-dom'
import ReactGridLayout from 'react-grid-layout'
import ReactResizeDetector from 'react-resize-detector'
import '../../../../node_modules/react-grid-layout/css/styles.css'
import '../../../../node_modules/react-resizable/css/styles.css'

/**
 * 主要是为了监听组件的 width 变化
 */
class MyReactGridLayout extends React.Component {
  constructor (props) {
    super(props)
    this.state = { width: 1200 }
  }

  componentDidMount () {
    this.mounted = true
    window.addEventListener('resize', this.onWindowResize, false)
    this.onWindowResize()
  }

  componentWillUnmount () {
    this.mounted = false
    window.removeEventListener('resize', this.onWindowResize)
  }

  onWindowResize = () => {
    if (!this.mounted) return
    const node = ReactDOM.findDOMNode(this)
    if (node instanceof HTMLElement) this.setState({ width: node.offsetWidth })
  }

  _onResize = (e) => {
    this.setState({ width: e })
  }
  render () {
    return (
      <div>
        <ReactResizeDetector handleWidth onResize={this._onResize.bind(this)} />
        <ReactGridLayout {...this.props} width={this.state.width} />
      </div>
    )
  }
}

export default MyReactGridLayout

