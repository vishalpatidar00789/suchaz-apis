/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ActivityListItemSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz-delete-dialog.component';
import { ActivityListItemSuchazService } from '../../../../../../main/webapp/app/entities/activity-list-item-suchaz/activity-list-item-suchaz.service';

describe('Component Tests', () => {

    describe('ActivityListItemSuchaz Management Delete Component', () => {
        let comp: ActivityListItemSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<ActivityListItemSuchazDeleteDialogComponent>;
        let service: ActivityListItemSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ActivityListItemSuchazDeleteDialogComponent],
                providers: [
                    ActivityListItemSuchazService
                ]
            })
            .overrideTemplate(ActivityListItemSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ActivityListItemSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActivityListItemSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
