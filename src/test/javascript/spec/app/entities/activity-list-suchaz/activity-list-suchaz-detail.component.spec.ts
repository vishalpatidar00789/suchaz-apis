/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { ActivityListSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/activity-list-suchaz/activity-list-suchaz-detail.component';
import { ActivityListSuchazService } from '../../../../../../main/webapp/app/entities/activity-list-suchaz/activity-list-suchaz.service';
import { ActivityListSuchaz } from '../../../../../../main/webapp/app/entities/activity-list-suchaz/activity-list-suchaz.model';

describe('Component Tests', () => {

    describe('ActivityListSuchaz Management Detail Component', () => {
        let comp: ActivityListSuchazDetailComponent;
        let fixture: ComponentFixture<ActivityListSuchazDetailComponent>;
        let service: ActivityListSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ActivityListSuchazDetailComponent],
                providers: [
                    ActivityListSuchazService
                ]
            })
            .overrideTemplate(ActivityListSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ActivityListSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActivityListSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ActivityListSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.activityList).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
