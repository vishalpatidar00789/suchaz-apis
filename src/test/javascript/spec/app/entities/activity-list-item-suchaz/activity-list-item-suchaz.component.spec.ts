/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { ActivityListItemSuchazComponent } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz.component';
import { ActivityListItemSuchazService } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz.service';
import { ActivityListItemSuchaz } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz.model';

describe('Component Tests', () => {

    describe('ActivityListItemSuchaz Management Component', () => {
        let comp: ActivityListItemSuchazComponent;
        let fixture: ComponentFixture<ActivityListItemSuchazComponent>;
        let service: ActivityListItemSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ActivityListItemSuchazComponent],
                providers: [
                    ActivityListItemSuchazService
                ]
            })
            .overrideTemplate(ActivityListItemSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ActivityListItemSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActivityListItemSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ActivityListItemSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.activityListItems[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
