import { ITraining } from 'app/shared/model/training.model';

export interface IWeeklyProgram {
  id?: number;
  week?: number;
  trainings?: ITraining[];
}

export class WeeklyProgram implements IWeeklyProgram {
  constructor(public id?: number, public week?: number, public trainings?: ITraining[]) {}
}
