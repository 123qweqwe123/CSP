import React, { Component } from 'react'
import { StyleSheet, View, Image, FlatList, Text, ScrollView, Dimensions, TouchableOpacity } from 'react-native'
import { connect } from 'react-redux'
import { List, ListItem, SearchBar, Button } from 'react-native-elements'

import { NavigationActions } from '../utils'

const SCREEN_HEIGHT = Dimensions.get('window').height;

@connect(({ home }) => ({ ...home }))
class Home extends Component {
  static navigationOptions = ({ navigation }) => {
    const { navigate } = navigation
    return {
      title: '会议',
    tabBarLabel: '会议',
    headerRight: 
      <TouchableOpacity onPress={() => navigate('Camera')}><Image
        style={{ width: 16, height: 16, marginRight: 20 }}
        source={require('../images/qr-scanner.png')}
      /></TouchableOpacity>,
    tabBarIcon: ({ focused, tintColor }) => (
      <Image
        style={[styles.icon, { tintColor: focused ? tintColor : 'gray' }]}
        source={require('../images/house.png')}
      />
    ),
    }
  }

  gotoLogin = () => {
    console.log(333)
    this.props.dispatch(NavigationActions.navigate({ routeName: 'Login' }))
  }

  gotoDetail = (confId) => {
    this.props.dispatch(NavigationActions.navigate({ routeName: 'Detail', params: { confId } }))
  }

  searchConference = (val) => {
    this.props.dispatch({ type: 'home/queryConferences', payload: { val } })
  }

  onEndReached = (info) => {
    console.log(info)
  }

  onRefresh = (info) => {
    console.log('refresh')
    this.props.dispatch({ type: 'home/queryConferences' })
  }

  _keyExtractor = (item, index) => item.id;

  closePopover = () => {
    console.log('close')
  }

  render() {
    return (
      <View>
        <SearchBar
          inputStyle={{ backgroundColor: 'rgb(218, 218, 218)' }}
          containerStyle={{ backgroundColor: 'rgb(250, 250, 250)', borderBottomWidth: 0, borderTopWidth: 0 }}
          onChangeText={this.searchConference}
          onClearText={this.searchConference}
          cancelButtonTitle="cancel"
          clearIcon={{ name: 'close' }}
          placeholder='搜索' />
        <List containerStyle={{ marginBottom: 10, marginTop: 0 }}>
          <FlatList
            keyExtractor={this._keyExtractor}
            data={this.props.list}
            onEndReached={this.onEndReached}
            onRefresh={this.onRefresh}
            refreshing={false}
            inverted={false}
            initialNumToRender={10}
            contentContainerStyle={{ paddingBottom: 250 }}
            style={{ minHeight: SCREEN_HEIGHT }}
            renderItem={({ item }) => <ListItem
              roundAvatar
              avatar={require('../images/metting.png')}
              key={item.id}
              title={item.confName}
              subtitle={item.confNo}
              onPress={() => this.gotoDetail(item.id)}
            />}
          />
        </List>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  icon: {
    width: 32,
    height: 32,
  },
  contentContainer: {
    paddingVertical: 1
  }
})

export default Home
