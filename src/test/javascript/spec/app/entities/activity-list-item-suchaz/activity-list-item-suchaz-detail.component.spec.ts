/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { ActivityListItemSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz-detail.component';
import { ActivityListItemSuchazService } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz.service';
import { ActivityListItemSuchaz } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz.model';

describe('Component Tests', () => {

    describe('ActivityListItemSuchaz Management Detail Component', () => {
        let comp: ActivityListItemSuchazDetailComponent;
        let fixture: ComponentFixture<ActivityListItemSuchazDetailComponent>;
        let service: ActivityListItemSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ActivityListItemSuchazDetailComponent],
                providers: [
                    ActivityListItemSuchazService
                ]
            })
            .overrideTemplate(ActivityListItemSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ActivityListItemSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActivityListItemSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ActivityListItemSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.activityListItem).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
