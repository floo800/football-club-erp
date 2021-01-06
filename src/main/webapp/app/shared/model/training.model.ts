import { Moment } from 'moment';

export interface ITraining {
  id?: number;
  startDate?: Moment;
  endDate?: Moment;
  name?: string;
  teamId?: number;
  weeklyProgramId?: number;
}

export class Training implements ITraining {
  constructor(
    public id?: number,
    public startDate?: Moment,
    public endDate?: Moment,
    public name?: string,
    public teamId?: number,
    public weeklyProgramId?: number
  ) {}
}
