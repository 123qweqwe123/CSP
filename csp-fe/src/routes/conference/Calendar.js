import React from 'react'
import PropTypes from 'prop-types'
import BigCalendar from 'react-big-calendar'
import moment from 'moment'
import 'react-big-calendar/lib/css/react-big-calendar.css'
import { routerRedux } from 'dva/router'
import { Form, Switch, Icon, Popover, Button } from 'antd'
import styles from './Calendar.less'

BigCalendar.momentLocalizer(moment)
const FormItem = Form.Item

const Calendar = ({
  events, toConf,
  addCourse, updateCourse, deleteCourse, switchItem, currentConfId,
  confChecked,
  courseChecked,
  checkinChecked,
}) => {
  const eventStyles = {
    event1: {
      backgroundColor: '#108ee9',
      color: 'white',
      borderRadius: 0,
    },
    event2: {
      backgroundColor: '#f56a00',
      color: 'white',
      borderRadius: 0,
    },
    event3: {
      backgroundColor: '#00a854',
      color: 'white',
      borderRadius: 0,
    },
    event: {
      backgroundColor: '#00a2ae',
      color: 'white',
      borderRadius: 0,
    },
  }

  return (
    <div style={{ height: 800 }}>
      <Form layout="inline" style={{ marginBottom: 20 }}>
        { currentConfId && <FormItem label="会议">
          <Switch checked={confChecked} onChange={checked => switchItem(1, checked)} style={{ backgroundColor: '#108ee9' }} checkedChildren={<Icon type="check" />} unCheckedChildren={<Icon type="cross" />} />
        </FormItem>
        }
        { currentConfId && <FormItem label="课程">
          <Switch checked={courseChecked} disabled={!currentConfId} onChange={checked => switchItem(2, checked)} style={{ backgroundColor: '#f56a00' }} checkedChildren={<Icon type="check" />} unCheckedChildren={<Icon type="cross" />} />
        </FormItem>
        }
        {/* <FormItem label="会场">
          <Switch style={{ backgroundColor: '#00a2ae' }} checkedChildren={<Icon type="check" />} unCheckedChildren={<Icon type="cross" />} />
        </FormItem> */}
        { currentConfId && <FormItem label="签到">
          <Switch checked={checkinChecked} disabled={!currentConfId} onChange={checked => switchItem(3, checked)} style={{ backgroundColor: '#00a854' }} checkedChildren={<Icon type="check" />} unCheckedChildren={<Icon type="cross" />} />
        </FormItem>
        }
      </Form>
      <BigCalendar
        style={{ height: 600 }}
        events={events}
        views={['month', 'week', 'day', 'agenda']}
        timeslots={6}
        step={60}
        popup
        // defaultDate={new Date(2015, 3, 1)}
        // onSelectEvent={event => alert(event.title)}
        eventPropGetter={(event) => {
          if (event.type) {
            return { style: eventStyles[`event${event.type}`] }
          }
          return { style: eventStyles.event }
        }}
        titleAccessor={(event) => {
          if (!currentConfId) {
            return <span className={styles.event} onDoubleClick={e => toConf(e, event.id)}>{event.title}</span>
          }
          if (event.type === 1) {
            return (<Popover trigger="click"
              content={<div><p style={{ marginTop: 5 }}><Button onClick={() => addCourse(event)}>添加课程</Button></p></div>}
            >
              <span className={styles.event}>{event.title}</span>
            </Popover>)
          } else if (event.type === 2) {
            return (<Popover trigger="click"
              content={<div><p style={{ marginTop: 5 }}><Button onClick={() => updateCourse(event)}>修改课程</Button></p><p style={{ marginTop: 5 }}><Button onClick={() => deleteCourse(event)}>删除课程</Button></p></div>}
            >
              <span className={styles.event}>{event.title}</span>
            </Popover>)
          }
          return event.title
        }}
      />
    </div>
  )
}

Calendar.propTypes = {
  location: PropTypes.object,
  loading: PropTypes.any,
  events: PropTypes.array,
  switchItem: PropTypes.func,
}

export default Calendar
