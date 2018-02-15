/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { OccassionSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/occassion-suchaz/occassion-suchaz-delete-dialog.component';
import { OccassionSuchazService } from '../../../../../../main/webapp/app/entities/occassion-suchaz/occassion-suchaz.service';

describe('Component Tests', () => {

    describe('OccassionSuchaz Management Delete Component', () => {
        let comp: OccassionSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<OccassionSuchazDeleteDialogComponent>;
        let service: OccassionSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [OccassionSuchazDeleteDialogComponent],
                providers: [
                    OccassionSuchazService
                ]
            })
            .overrideTemplate(OccassionSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OccassionSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OccassionSuchazService);
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
