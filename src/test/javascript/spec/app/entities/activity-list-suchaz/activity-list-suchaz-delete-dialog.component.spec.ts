/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ActivityListSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/activity-list-suchaz/activity-list-suchaz-delete-dialog.component';
import { ActivityListSuchazService } from '../../../../../../main/webapp/app/entities/activity-list-suchaz/activity-list-suchaz.service';

describe('Component Tests', () => {

    describe('ActivityListSuchaz Management Delete Component', () => {
        let comp: ActivityListSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<ActivityListSuchazDeleteDialogComponent>;
        let service: ActivityListSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ActivityListSuchazDeleteDialogComponent],
                providers: [
                    ActivityListSuchazService
                ]
            })
            .overrideTemplate(ActivityListSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ActivityListSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActivityListSuchazService);
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
