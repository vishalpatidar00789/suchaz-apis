/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { ActivityListSuchazComponent } from '../../../../../../main/webapp/app/entities/activity-list-suchaz/activity-list-suchaz.component';
import { ActivityListSuchazService } from '../../../../../../main/webapp/app/entities/activity-list-suchaz/activity-list-suchaz.service';
import { ActivityListSuchaz } from '../../../../../../main/webapp/app/entities/activity-list-suchaz/activity-list-suchaz.model';

describe('Component Tests', () => {

    describe('ActivityListSuchaz Management Component', () => {
        let comp: ActivityListSuchazComponent;
        let fixture: ComponentFixture<ActivityListSuchazComponent>;
        let service: ActivityListSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ActivityListSuchazComponent],
                providers: [
                    ActivityListSuchazService
                ]
            })
            .overrideTemplate(ActivityListSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ActivityListSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActivityListSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ActivityListSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.activityLists[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
