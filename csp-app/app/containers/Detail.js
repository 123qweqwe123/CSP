import React, { Component } from 'react'
import { StyleSheet, View, Dimensions, StatusBar, ScrollView, Text } from 'react-native'
import { connect } from 'react-redux'
import moment from 'moment'

import { NavigationActions } from '../utils'
import { Button } from 'react-native-elements';


const SCREEN_WIDTH = Dimensions.get('window').width;
const SCREEN_HEIGHT = Dimensions.get('window').height;

class CustomButton extends Component {
  constructor() {
    super();

    this.state = {
      selected: false
    };
  }

  componentDidMount() {
    const { selected } = this.props;

    this.setState({
      selected
    });
  }

  render() {
    const { title } = this.props;
    const { selected } = this.state;

    return (
      <Button
        title={title}
        textStyle={{ fontSize: 15, fontWeight: '700' }}
        buttonStyle={{ backgroundColor: 'rgba(213, 100, 140, 1)', borderRadius: 100, width: 127 }}
        containerStyle={{ marginRight: 10 }}
        onPress={() => this.setState({ selected: !selected })}
      />
    );
  }
}

const statusList = {
  1: '准备中',
  2: '进行中',
  3: '结束',
  4: '完结',
}
@connect(({ home }) => ({ ...home }))
class Detail extends Component {
  static navigationOptions = {
    title: '会议明细',
  }

  gotoDetail = () => {
    this.props.dispatch(NavigationActions.navigate({ routeName: 'Detail' }))
  }

  goBack = () => {
    this.props.dispatch(NavigationActions.back({ routeName: 'Account' }))
  }

  componentDidMount() {
    // 获取签到信息
  }

  componentWillUnmount() {
    console.log('unmount')
  }

  render() {
    const currentConference = this.props.list.filter(x => x.id === this.props.navigation.state.params.confId)[0]
    const startDate = moment(currentConference.startTime).format('YYYY-MM-DD')
    const endDate = moment(currentConference.endTime).format('yyyy-MM-dd')
    return (
      <View style={{ flex: 1, backgroundColor: 'rgb(236,235,243)' }}>
        <ScrollView style={{ flex: 1 }}>
          <View style={{ flex: 1, marginTop: 30 }}>
            <Text style={{ flex: 1, fontSize: 15, color: 'rgba(216, 121, 112, 1)', marginLeft: 40 }}>
              详细信息：
            </Text>
            <View style={{ flex: 1, borderRadius: 10, flexDirection: 'row', marginTop: 20, marginHorizontal: 30, justifyContent: 'center', backgroundColor: 'rgb(255,255,255)' }}>
              <View style={{ flex: 1, flexDirection: 'row' }}>
                <View style={{ flex: 1 }}>
                  <Text style={styles.infoTypeLabel}>会议名称</Text>
                  <Text style={styles.infoTypeLabel}>会议编号</Text>
                  <Text style={styles.infoTypeLabel}>主办单位</Text>
                  <Text style={styles.infoTypeLabel}>开始日期</Text>
                  <Text style={styles.infoTypeLabel}>结束日期</Text>
                  <Text style={styles.infoTypeLabel}>会议地点</Text>
                  <Text style={styles.infoTypeLabel}>会议状态</Text>
                </View>
                <View style={{ flex: 2, marginLeft: 10 }}>
                  <Text style={styles.infoAnswerLabel}>{currentConference.confName}</Text>
                  <Text style={styles.infoAnswerLabel}>{currentConference.confNo}</Text>
                  <Text style={styles.infoAnswerLabel}>{currentConference.confOrganiser}</Text>
                  <Text style={styles.infoAnswerLabel}>{startDate}</Text>
                  <Text style={styles.infoAnswerLabel}>{endDate}</Text>
                  <Text style={styles.infoAnswerLabel}>{currentConference.placeName}</Text>
                  <Text style={styles.infoAnswerLabel}>{statusList[currentConference.status]}</Text>
                </View>
              </View>
            </View>
          </View>
          <View style={{ flex: 1, marginTop: 30 }}>
            <Text style={{ flex: 1, fontSize: 15, color: 'rgba(216, 121, 112, 1)', marginLeft: 40 }}>
              操作
            </Text>
            <View style={{ flex: 1, width: SCREEN_WIDTH, marginTop: 20 }}>
              <ScrollView
                style={{ flex: 1 }}
                horizontal
                showsHorizontalScrollIndicator={false}
              >
                <View style={{ flex: 1, flexDirection: 'column', height: 170, marginLeft: 40, marginRight: 10 }}>
                  <View style={{ flex: 1, flexDirection: 'row' }}>
                    <CustomButton title="签到查看" />
                    <CustomButton title="课程查看" />
                  </View>
                </View>
              </ScrollView>
            </View>
          </View>
        </ScrollView>
      </View>
    )
  }
}


const styles = StyleSheet.create({
  statusBar: {
    height: 10,
  },
  navBar: {
    height: 60,
    width: SCREEN_WIDTH,
    justifyContent: 'center',
    alignContent: 'center'
  },
  nameHeader: {
    fontSize: 22,
    textAlign: 'center'
  },
  infoTypeLabel: {
    fontSize: 15,
    flex: 1,
    textAlign: 'right',
    paddingBottom: 10,
    marginTop: 10,
    alignSelf: 'center',
  },
  infoAnswerLabel: {
    fontSize: 15,
    flex: 1,
    color: 'blue',
    textAlign: 'left',
    paddingBottom: 10,
    marginTop: 10,
  }
});

export default Detail
