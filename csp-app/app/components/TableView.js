import React, { Component } from 'react'
import { StyleSheet, View, Dimensions, StatusBar, ScrollView, Text } from 'react-native'

class TableView extends Component {
    render() {
        const { height, columns, dataSource } = this.props
        console.log(columns, dataSource)
        return <View style={{ height: height, padding: 5 }}>
            <View style={[styles.tableRow, styles.tableRowHeader]}>
                {
                    columns.map((title, k) =>
                        <View key={k} style={styles.tableCell}><Text style={styles.headerText}>{title}</Text></View>
                    )
                }
            </View>
            <ScrollView style={styles.tableBody}>
                {
                    dataSource.map((row, k) =>
                        <View key={k} style={styles.tableRow}>
                            {
                                row.map((cell, i) =>
                                    <View key={i} style={styles.tableCell}><Text style={styles.text}>{cell}</Text></View>
                                )
                            }
                        </View>
                    )
                }
            </ScrollView>
        </View>
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
export default TableView