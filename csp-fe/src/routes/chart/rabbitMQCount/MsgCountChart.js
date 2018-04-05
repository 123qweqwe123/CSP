import React from 'react'
import PropTypes from 'prop-types'
import {
  ResponsiveContainer,
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from 'recharts'

const MsgCountChart = ({ data }) => {
  /**
   * @return {XML}
   */
  const CustomTooltip = (props) => {
    const {
      active, payload, external, label,
    } = props
    if (active) {
      const style = {
        padding: 5,
        backgroundColor: '#fff',
        border: '1px solid #ccc',
      }

      const currData = external.filter(entry => (entry.time === label))[0]

      return (
        <div className="area-chart-tooltip" style={style}>
          <p>{currData.time}</p>
          <p style={{ color: '#95de64' }}>person : {currData.person} 条</p>
          <p style={{ color: '#5cdbd3' }}>register : {currData.register} 条</p>
          <p style={{ color: '#69c0ff' }}>checkin : {currData.checkin} 条</p>
        </div>
      )
    }
    return null
  }

  return (
    <div style={{ height: 'calc(100vh - 200px)', width: '100%' }}>
      <ResponsiveContainer>
        <LineChart data={data}
          margin={{
           top: 5, right: 30, left: 20, bottom: 5,
          }}
        >
          <XAxis dataKey="time" />
          <YAxis />
          <Legend verticalAlign="top" height={36} />
          <Tooltip content={<CustomTooltip external={data} />} />
          <CartesianGrid strokeDasharray="3 3" />
          <Line type="monotone" dataKey="person" stroke="#95de64" />
          <Line type="monotone" dataKey="register" stroke="#5cdbd3" />
          <Line type="monotone" dataKey="checkin" stroke="#69c0ff" />
        </LineChart>
      </ResponsiveContainer>
    </div>
  )
}

MsgCountChart.propTypes = {
  data: PropTypes.array,
}

export default MsgCountChart
