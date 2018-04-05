import React, { Component } from 'react'
import { StyleSheet, View, Dimensions, StatusBar, ScrollView, Text } from 'react-native'
import { connect } from 'react-redux'

import { NavigationActions } from '../utils'
import { Button, SearchBar } from 'react-native-elements';
import TableView from '../components/TableView'


const SCREEN_WIDTH = Dimensions.get('window').width;
const SCREEN_HEIGHT = Dimensions.get('window').height;

@connect(({ home }) => ({ ...home }))
class Checkin extends Component {
    static navigationOptions = {
        title: '签到信息',
    }
    componentDidMount() {
        // 获取签到信息
    }

    componentWillUnmount() {
    }
    searchConference(v) {
        console.log(v)
    }

    render() {
        const config = {
            height: 200,
            columns: ['省份', '单位', '姓名', '电话'],
            dataSource: [
                ['河南省', '信阳', '信阳', '1350121785'],
                ['河南省', '信阳', '信阳', '1350121785'],
                ['河南省', '信阳', '信阳', '1350121785'],
                ['河南省', '信阳', '信阳', '1350121785'],
                ['河南省', '信阳', '信阳', '1350121785'],
                ['河南省', '信阳', '信阳', '1350121785'],
                ['河南省', '信阳', '信阳', '1350121785'],
                ['河南省', '信阳', '信阳', '1350121785'],
                ['河南省', '信阳', '信阳', '1350121785'],
            ]
        }
        return (
            <View style={{ flex: 1, backgroundColor: 'rgb(236,235,243)' }}>
                <SearchBar
                    inputStyle={{ backgroundColor: 'rgb(218, 218, 218)' }}
                    containerStyle={{ backgroundColor: 'rgb(250, 250, 250)', borderBottomWidth: 0, borderTopWidth: 0 }}
                    onChangeText={this.searchConference}
                    onClearText={this.searchConference}
                    cancelButtonTitle="cancel"
                    clearIcon={{ name: 'close' }}
                    placeholder='姓名' />
                <View style={{ marginTop: 10 }}><Text style={{ color: 'rgba(216, 121, 112, 1)' }}>未签到(20)人</Text></View>
                <TableView {...config} />
                <View><Text style={{ color: 'rgba(216, 121, 112, 1)'}}>已签到(10)人</Text></View>
                <TableView {...config} />
            </View>
        )
    }
}


const styles = StyleSheet.create({
    tableRow: {
        flexDirection: 'row',
        marginTop: 5,
    },
    tableBody: {
        borderBottomWidth: 1,
        borderBottomColor: '#d4b106',
    },
    tableRowHeader: {
        backgroundColor: '#FFEEDB',
        paddingTop: 8,
        paddingBottom: 8,
    },
    tableCell: {
        flex: 1,
    },
    headerText: {
        color: 'black',
        fontWeight: '700',
    },
    text: {
        color: 'blue'
    }
});

export default Checkin
