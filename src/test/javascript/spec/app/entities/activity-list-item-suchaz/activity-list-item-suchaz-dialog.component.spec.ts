/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ActivityListItemSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz-dialog.component';
import { ActivityListItemSuchazService } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz.service';
import { ActivityListItemSuchaz } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz.model';
import { ActivityListSuchazService } from '../../../../../../main/webapp/app/entities/activity-list-suchaz';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz';

describe('Component Tests', () => {

    describe('ActivityListItemSuchaz Management Dialog Component', () => {
        let comp: ActivityListItemSuchazDialogComponent;
        let fixture: ComponentFixture<ActivityListItemSuchazDialogComponent>;
        let service: ActivityListItemSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ActivityListItemSuchazDialogComponent],
                providers: [
                    ActivityListSuchazService,
                    ItemSuchazService,
                    ActivityListItemSuchazService
                ]
            })
            .overrideTemplate(ActivityListItemSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ActivityListItemSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActivityListItemSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ActivityListItemSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.activityListItem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'activityListItemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ActivityListItemSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.activityListItem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'activityListItemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
