import React from 'react';
import { Droppable } from 'react-beautiful-dnd';
import styled from 'styled-components';
import JobApplication from '../JobApplication/JobApplication';

const Column = ({columnTitle, jobApp, jobAppId}) => {
  return (
    <>
    <Droppable droppableId={id}>
      {/* Snapshots are objects that provide info about the current state of a droppable/draggable component during a drag/drop operation */}
      {(provided, snapshot) => {
        <JobApplication>
          ref={provided.innerRef}
          {...provided.droppableProps}
          isDraggingver={snapshot.isDraggingOver}
          {provided.placeholder}
        </JobApplication>
      }}
    </Droppable>
    </>
  );
}

export default Column;